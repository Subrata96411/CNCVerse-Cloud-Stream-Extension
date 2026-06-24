package com.cncverse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.utils.AppUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: PlayZTVProviderManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006H\u0002J\u000e\u0010\r\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010\u0011J \u0010\u0012\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00130\u0005H\u0086@¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0005H\u0086@¢\u0006\u0002\u0010\u000eJ\u001e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00052\u0006\u0010\u0018\u001a\u00020\u0006H\u0086@¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/cncverse/PlayZTVProviderManager;", "", "<init>", "()V", "DEFAULT_BASE_URLS", "", "", "cachedBaseUrl", "client", "Lokhttp3/OkHttpClient;", "parseDateTime", "date", "time", "getBaseUrl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchDecrypted", "path", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchProviders", "", "fetchLiveEvents", "Lcom/cncverse/PlayZLiveEventData;", "fetchChannelStreams", "Lcom/cncverse/PlayZStreamUrl;", "slug", "PlayZTVProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPlayZTVProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,275:1\n1#2:276\n*E\n"})
public final class PlayZTVProviderManager {

    @Nullable
    private static String cachedBaseUrl;

    @NotNull
    public static final PlayZTVProviderManager INSTANCE = new PlayZTVProviderManager();

    @NotNull
    private static final List<String> DEFAULT_BASE_URLS = CollectionsKt.listOf(new String[]{"https://adsflw.xyz", "https://playztv2828.store"});

    @NotNull
    private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

    /* JADX INFO: renamed from: com.cncverse.PlayZTVProviderManager$getBaseUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayZTVProviderManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVProviderManager", f = "PlayZTVProviderManager.kt", i = {}, l = {119}, m = "getBaseUrl", n = {}, nl = {120}, s = {}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlayZTVProviderManager.this.getBaseUrl((Continuation) this);
        }
    }

    private PlayZTVProviderManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseDateTime(String date, String time) {
        if (date == null || time == null) {
            return null;
        }
        try {
            List parts = StringsKt.split$default(date, new String[]{"/"}, false, 0, 6, (Object) null);
            if (parts.size() != 3) {
                return null;
            }
            String day = (String) parts.get(0);
            String month = (String) parts.get(1);
            String year = (String) parts.get(2);
            return year + '/' + month + '/' + day + ' ' + time + " +0000";
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getBaseUrl(kotlin.coroutines.Continuation<? super java.lang.String> r10) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVProviderManager.getBaseUrl(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVProviderManager$fetchDecrypted$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayZTVProviderManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVProviderManager$fetchDecrypted$2", f = "PlayZTVProviderManager.kt", i = {}, l = {146}, m = "invokeSuspend", n = {}, nl = {147}, s = {}, v = 2)
    static final class C00062 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ String $path;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00062(String str, Continuation<? super C00062> continuation) {
            super(2, continuation);
            this.$path = str;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00062(this.$path, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object baseUrl;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    baseUrl = PlayZTVProviderManager.INSTANCE.getBaseUrl((Continuation) this);
                    if (baseUrl == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    baseUrl = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            String baseUrl2 = (String) baseUrl;
            String url = baseUrl2 + '/' + this.$path;
            String strDecryptPlayZTV = null;
            try {
                Request request = new Request.Builder().url(url).header("User-Agent", "Dalvik/2.1.0 (Linux; U; Android 10; SM-A505F)").build();
                Response response = PlayZTVProviderManager.client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String body = response.body().string();
                    if (!StringsKt.isBlank(body)) {
                        strDecryptPlayZTV = PlayZTVCryptoUtils.INSTANCE.decryptPlayZTV(StringsKt.trim(body).toString());
                    }
                } else {
                    System.out.println((Object) ("PlayZTV: HTTP " + response.code() + " fetching " + url));
                }
            } catch (Exception e) {
                System.out.println((Object) ("PlayZTV: Exception fetching " + url + " – " + e.getMessage()));
            }
            return strDecryptPlayZTV;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchDecrypted(String path, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C00062(path, null), continuation);
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVProviderManager$fetchProviders$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayZTVProviderManager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVProviderManager$fetchProviders$2", f = "PlayZTVProviderManager.kt", i = {}, l = {175}, m = "invokeSuspend", n = {}, nl = {176}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nPlayZTVProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager$fetchProviders$2\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,275:1\n63#2:276\n64#2,15:278\n63#2:299\n64#2,15:301\n1#3:277\n1#3:300\n1#3:318\n50#4:293\n43#4:294\n50#4:316\n43#4:317\n1606#5:295\n1617#5:296\n1924#5,2:297\n1926#5:319\n1618#5:320\n*S KotlinDebug\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager$fetchProviders$2\n*L\n177#1:276\n177#1:278,15\n180#1:299\n180#1:301,15\n177#1:277\n180#1:300\n178#1:318\n177#1:293\n177#1:294\n180#1:316\n180#1:317\n178#1:295\n178#1:296\n178#1:297,2\n178#1:319\n178#1:320\n*E\n"})
    static final class C00082 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Map<String, ? extends Object>>>, Object> {
        int label;

        C00082(Continuation<? super C00082> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00082(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Map<String, ? extends Object>>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:108:0x0226  */
        /* JADX WARN: Removed duplicated region for block: B:119:0x0263 A[Catch: Exception -> 0x001c, TryCatch #9 {Exception -> 0x001c, blocks: (B:6:0x0016, B:14:0x0033, B:16:0x003a, B:22:0x0046, B:28:0x0078, B:36:0x00a7, B:39:0x00ae, B:49:0x00de, B:50:0x00f7, B:52:0x00fd, B:54:0x0105, B:55:0x0108, B:119:0x0263, B:116:0x0237, B:121:0x026e, B:48:0x00c9, B:47:0x00c3, B:35:0x009c, B:27:0x006e, B:11:0x0023, B:24:0x004b, B:32:0x0081, B:42:0x00b4), top: B:138:0x000b, inners: #6, #12, #16 }] */
        /* JADX WARN: Removed duplicated region for block: B:130:0x01e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:150:0x0191 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:154:0x0267 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:82:0x018a  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01b7  */
        /* JADX WARN: Type inference failed for: r31v4, types: [boolean] */
        /* JADX WARN: Type inference failed for: r31v5 */
        /* JADX WARN: Type inference failed for: r31v6 */
        /* JADX WARN: Type inference failed for: r31v7 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r34) {
            /*
                Method dump skipped, instruction units count: 670
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVProviderManager.C00082.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Nullable
    public final Object fetchProviders(@NotNull Continuation<? super List<? extends Map<String, ? extends Object>>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C00082(null), continuation);
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVProviderManager$fetchLiveEvents$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayZTVProviderManager.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/cncverse/PlayZLiveEventData;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVProviderManager$fetchLiveEvents$2", f = "PlayZTVProviderManager.kt", i = {}, l = {206}, m = "invokeSuspend", n = {}, nl = {207}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nPlayZTVProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager$fetchLiveEvents$2\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,275:1\n63#2:276\n64#2,15:278\n63#2:299\n64#2,15:301\n1#3:277\n1#3:300\n1#3:322\n50#4:293\n43#4:294\n50#4:316\n43#4:317\n1606#5:295\n1617#5:296\n1924#5,2:297\n1586#5:318\n1661#5,3:319\n1926#5:323\n1618#5:324\n777#5:325\n873#5,2:326\n*S KotlinDebug\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager$fetchLiveEvents$2\n*L\n208#1:276\n208#1:278,15\n211#1:299\n211#1:301,15\n208#1:277\n211#1:300\n209#1:322\n208#1:293\n208#1:294\n211#1:316\n211#1:317\n209#1:295\n209#1:296\n209#1:297,2\n232#1:318\n232#1:319,3\n209#1:323\n209#1:324\n241#1:325\n241#1:326,2\n*E\n"})
    static final class C00072 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PlayZLiveEventData>>, Object> {
        int label;

        C00072(Continuation<? super C00072> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00072(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<PlayZLiveEventData>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:112:0x024b  */
        /* JADX WARN: Removed duplicated region for block: B:113:0x024e  */
        /* JADX WARN: Removed duplicated region for block: B:116:0x0256 A[Catch: Exception -> 0x02b7, TryCatch #3 {Exception -> 0x02b7, blocks: (B:75:0x017e, B:78:0x0185, B:96:0x01c6, B:99:0x01d3, B:103:0x01e3, B:110:0x01f3, B:114:0x0250, B:116:0x0256, B:117:0x026d, B:119:0x0273, B:95:0x01ad, B:93:0x01a4, B:74:0x0174), top: B:152:0x0185 }] */
        /* JADX WARN: Removed duplicated region for block: B:123:0x02a3 A[Catch: Exception -> 0x02b5, TryCatch #4 {Exception -> 0x02b5, blocks: (B:121:0x028b, B:122:0x0298, B:124:0x02ad, B:123:0x02a3), top: B:154:0x028b }] */
        /* JADX WARN: Removed duplicated region for block: B:135:0x02ed A[Catch: Exception -> 0x001c, TryCatch #8 {Exception -> 0x001c, blocks: (B:6:0x0016, B:14:0x0033, B:16:0x003a, B:22:0x0046, B:28:0x0078, B:36:0x00a7, B:39:0x00ae, B:49:0x00de, B:50:0x00f7, B:52:0x00fd, B:54:0x0105, B:55:0x0108, B:132:0x02c0, B:135:0x02ed, B:137:0x02fe, B:138:0x0317, B:140:0x031d, B:145:0x0331, B:147:0x0337, B:48:0x00c9, B:47:0x00c3, B:35:0x009c, B:27:0x006e, B:11:0x0023, B:24:0x004b, B:32:0x0081, B:42:0x00b4), top: B:162:0x000b, inners: #5, #11, #15 }] */
        /* JADX WARN: Removed duplicated region for block: B:178:0x02f3 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x01d1  */
        /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r51) {
            /*
                Method dump skipped, instruction units count: 870
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVProviderManager.C00072.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Nullable
    public final Object fetchLiveEvents(@NotNull Continuation<? super List<PlayZLiveEventData>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C00072(null), continuation);
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVProviderManager$fetchChannelStreams$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlayZTVProviderManager.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/cncverse/PlayZStreamUrl;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVProviderManager$fetchChannelStreams$2", f = "PlayZTVProviderManager.kt", i = {}, l = {255}, m = "invokeSuspend", n = {}, nl = {256}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nPlayZTVProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager$fetchChannelStreams$2\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n*L\n1#1,275:1\n63#2:276\n64#2,15:278\n1#3:277\n50#4:293\n43#4:294\n*S KotlinDebug\n*F\n+ 1 PlayZTVProviderManager.kt\ncom/cncverse/PlayZTVProviderManager$fetchChannelStreams$2\n*L\n257#1:276\n257#1:278,15\n257#1:277\n257#1:293\n257#1:294\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PlayZStreamUrl>>, Object> {
        final /* synthetic */ String $slug;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$slug = str;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$slug, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<PlayZStreamUrl>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object objFetchDecrypted;
            Object obj;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            boolean z = true;
            Object objDecodeFromString = null;
            try {
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        this.label = 1;
                        objFetchDecrypted = PlayZTVProviderManager.INSTANCE.fetchDecrypted(this.$slug + ".txt", (Continuation) this);
                        if (objFetchDecrypted == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        objFetchDecrypted = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String decrypted = (String) objFetchDecrypted;
                String str = decrypted;
                if (str != null && !StringsKt.isBlank(str)) {
                    z = false;
                }
                if (!z) {
                    AppUtils appUtils = AppUtils.INSTANCE;
                    try {
                        Result.Companion companion = Result.Companion;
                        KType kTypeTypeOf = Reflection.typeOf(List.class, KTypeProjection.Companion.invariant(Reflection.typeOf(PlayZStreamUrl.class)));
                        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
                        obj = Result.constructor-impl(SerializersKt.serializer(kTypeTypeOf));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                    }
                    if (Result.exceptionOrNull-impl(obj) != null) {
                        try {
                            Result.Companion companion3 = Result.Companion;
                            obj = Result.constructor-impl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(List.class), (List) null, 2, (Object) null));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.Companion;
                            obj = Result.constructor-impl(ResultKt.createFailure(th2));
                        }
                    }
                    if (Result.isFailure-impl(obj)) {
                        obj = null;
                    }
                    DeserializationStrategy deserializationStrategy = (KSerializer) obj;
                    if (deserializationStrategy != null) {
                        try {
                            objDecodeFromString = MainAPIKt.getJson().decodeFromString(deserializationStrategy, decrypted);
                            return objDecodeFromString;
                        } catch (SerializationException e) {
                            ArchComponentExtKt.logError(e);
                        } catch (Throwable th3) {
                        }
                    }
                    ObjectMapper $this$readValue$iv$iv = MainAPIKt.getMapper();
                    return $this$readValue$iv$iv.readValue(decrypted, new TypeReference<List<? extends PlayZStreamUrl>>() { // from class: com.cncverse.PlayZTVProviderManager$fetchChannelStreams$2$invokeSuspend$$inlined$parseJson$1
                    });
                }
            } catch (Exception e2) {
                System.out.println((Object) ("PlayZTV: fetchChannelStreams exception for " + this.$slug + " – " + e2.getMessage()));
            }
            return objDecodeFromString;
        }
    }

    @Nullable
    public final Object fetchChannelStreams(@NotNull String slug, @NotNull Continuation<? super List<PlayZStreamUrl>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(slug, null), continuation);
    }
}
