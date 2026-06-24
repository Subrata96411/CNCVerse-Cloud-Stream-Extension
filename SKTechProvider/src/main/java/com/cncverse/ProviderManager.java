package com.cncverse;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProviderManager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0002J\u000e\u0010\u000f\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010\u0010J \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000e0\rH\u0086@¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0086@¢\u0006\u0002\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/cncverse/ProviderManager;", "", "<init>", "()V", "DEFAULT_BASE_URL", "", "cachedBaseUrl", "parseDateTime", "date", "time", "client", "Lokhttp3/OkHttpClient;", "fallbackProviders", "", "", "getBaseUrl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchProviders", "fetchLiveEvents", "Lcom/cncverse/LiveEventData;", "SKTechProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProviderManager.kt\ncom/cncverse/ProviderManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,294:1\n1#2:295\n*E\n"})
public final class ProviderManager {

    @NotNull
    private static final String DEFAULT_BASE_URL = "https://matkeritnagurorxbxb.store";

    @Nullable
    private static String cachedBaseUrl;

    @NotNull
    public static final ProviderManager INSTANCE = new ProviderManager();

    @NotNull
    private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

    @NotNull
    private static final List<Map<String, Object>> fallbackProviders = CollectionsKt.emptyList();

    /* JADX INFO: renamed from: com.cncverse.ProviderManager$getBaseUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProviderManager.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.ProviderManager", f = "ProviderManager.kt", i = {}, l = {129}, m = "getBaseUrl", n = {}, nl = {130}, s = {}, v = 2)
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
            return ProviderManager.this.getBaseUrl((Continuation) this);
        }
    }

    private ProviderManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseDateTime(String date, String time) {
        if (date == null || time == null) {
            return null;
        }
        try {
            List parts = StringsKt.split$default(date, new String[]{"/"}, false, 0, 6, (Object) null);
            if (parts.size() == 3) {
                String day = (String) parts.get(0);
                String month = (String) parts.get(1);
                String year = (String) parts.get(2);
                return year + '/' + month + '/' + day + ' ' + time + " +0000";
            }
        } catch (Exception e) {
            System.out.println((Object) ("SKTech: Failed to parse date/time: " + date + ' ' + time));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getBaseUrl(kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.cncverse.ProviderManager.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r6
            com.cncverse.ProviderManager$getBaseUrl$1 r0 = (com.cncverse.ProviderManager.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.cncverse.ProviderManager$getBaseUrl$1 r0 = new com.cncverse.ProviderManager$getBaseUrl$1
            r0.<init>(r6)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L32;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L46
        L32:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.String r3 = com.cncverse.ProviderManager.cachedBaseUrl
            if (r3 == 0) goto L3b
            r2 = 0
            return r3
        L3b:
            com.cncverse.FirebaseRemoteConfigFetcher r3 = com.cncverse.FirebaseRemoteConfigFetcher.INSTANCE
            r0.label = r4
            java.lang.Object r3 = r3.getBaseApiUrl(r0)
            if (r3 != r2) goto L46
            return r2
        L46:
            r2 = r3
            java.lang.String r2 = (java.lang.String) r2
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            if (r3 == 0) goto L56
            boolean r3 = kotlin.text.StringsKt.isBlank(r3)
            if (r3 == 0) goto L55
            goto L56
        L55:
            r4 = 0
        L56:
            if (r4 != 0) goto L60
            com.cncverse.ProviderManager.cachedBaseUrl = r2
            java.lang.String r3 = com.cncverse.ProviderManager.cachedBaseUrl
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            return r3
        L60:
            java.lang.String r3 = "https://matkeritnagurorxbxb.store"
            com.cncverse.ProviderManager.cachedBaseUrl = r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.ProviderManager.getBaseUrl(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.ProviderManager$fetchProviders$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProviderManager.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0005H\n"}, d2 = {"<anonymous>", "", "", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.ProviderManager$fetchProviders$2", f = "ProviderManager.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, nl = {145}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProviderManager.kt\ncom/cncverse/ProviderManager$fetchProviders$2\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,294:1\n63#2:295\n64#2,15:297\n63#2:318\n64#2,15:320\n1#3:296\n1#3:319\n1#3:337\n50#4:312\n43#4:313\n50#4:335\n43#4:336\n1606#5:314\n1617#5:315\n1924#5,2:316\n1926#5:338\n1618#5:339\n*S KotlinDebug\n*F\n+ 1 ProviderManager.kt\ncom/cncverse/ProviderManager$fetchProviders$2\n*L\n163#1:295\n163#1:297,15\n168#1:318\n168#1:320,15\n163#1:296\n168#1:319\n166#1:337\n163#1:312\n163#1:313\n168#1:335\n168#1:336\n166#1:314\n166#1:315\n166#1:316,2\n166#1:338\n166#1:339\n*E\n"})
    static final class C00142 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Map<String, ? extends Object>>>, Object> {
        int label;

        C00142(Continuation<? super C00142> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C00142(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Map<String, ? extends Object>>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:122:0x02a2 A[Catch: Exception -> 0x02ee, TryCatch #0 {Exception -> 0x02ee, blocks: (B:99:0x0248, B:102:0x024f, B:120:0x0292, B:122:0x02a2, B:125:0x02ca, B:119:0x0277, B:117:0x026e, B:98:0x023e), top: B:151:0x024f }] */
        /* JADX WARN: Removed duplicated region for block: B:131:0x02e8  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x032a A[Catch: Exception -> 0x001e, TryCatch #10 {Exception -> 0x001e, blocks: (B:6:0x0018, B:14:0x0033, B:16:0x0070, B:18:0x007d, B:24:0x0089, B:26:0x00bf, B:32:0x00cb, B:48:0x0129, B:56:0x0158, B:59:0x015f, B:69:0x0191, B:70:0x01aa, B:72:0x01b0, B:74:0x01b8, B:75:0x01bb, B:140:0x032a, B:137:0x02fb, B:142:0x0338, B:68:0x017a, B:67:0x0174, B:55:0x014d, B:47:0x011f, B:144:0x0343, B:145:0x034d, B:146:0x0357, B:11:0x0025, B:52:0x0131, B:62:0x0165), top: B:163:0x000d, inners: #3, #16 }] */
        /* JADX WARN: Removed duplicated region for block: B:153:0x0131 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:178:0x0165 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:182:0x032e A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:58:0x015e  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x017a A[Catch: Exception -> 0x001e, TryCatch #10 {Exception -> 0x001e, blocks: (B:6:0x0018, B:14:0x0033, B:16:0x0070, B:18:0x007d, B:24:0x0089, B:26:0x00bf, B:32:0x00cb, B:48:0x0129, B:56:0x0158, B:59:0x015f, B:69:0x0191, B:70:0x01aa, B:72:0x01b0, B:74:0x01b8, B:75:0x01bb, B:140:0x032a, B:137:0x02fb, B:142:0x0338, B:68:0x017a, B:67:0x0174, B:55:0x014d, B:47:0x011f, B:144:0x0343, B:145:0x034d, B:146:0x0357, B:11:0x0025, B:52:0x0131, B:62:0x0165), top: B:163:0x000d, inners: #3, #16 }] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x01b0 A[Catch: Exception -> 0x001e, TryCatch #10 {Exception -> 0x001e, blocks: (B:6:0x0018, B:14:0x0033, B:16:0x0070, B:18:0x007d, B:24:0x0089, B:26:0x00bf, B:32:0x00cb, B:48:0x0129, B:56:0x0158, B:59:0x015f, B:69:0x0191, B:70:0x01aa, B:72:0x01b0, B:74:0x01b8, B:75:0x01bb, B:140:0x032a, B:137:0x02fb, B:142:0x0338, B:68:0x017a, B:67:0x0174, B:55:0x014d, B:47:0x011f, B:144:0x0343, B:145:0x034d, B:146:0x0357, B:11:0x0025, B:52:0x0131, B:62:0x0165), top: B:163:0x000d, inners: #3, #16 }] */
        /* JADX WARN: Type inference failed for: r18v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r18v4 */
        /* JADX WARN: Type inference failed for: r18v5 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r39) {
            /*
                Method dump skipped, instruction units count: 936
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cncverse.ProviderManager.C00142.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Nullable
    public final Object fetchProviders(@NotNull Continuation<? super List<? extends Map<String, ? extends Object>>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C00142(null), continuation);
    }

    /* JADX INFO: renamed from: com.cncverse.ProviderManager$fetchLiveEvents$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProviderManager.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/cncverse/LiveEventData;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.ProviderManager$fetchLiveEvents$2", f = "ProviderManager.kt", i = {}, l = {214}, m = "invokeSuspend", n = {}, nl = {215}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nProviderManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProviderManager.kt\ncom/cncverse/ProviderManager$fetchLiveEvents$2\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,294:1\n63#2:295\n64#2,15:297\n63#2:318\n64#2,15:320\n1#3:296\n1#3:319\n1#3:341\n50#4:312\n43#4:313\n50#4:335\n43#4:336\n1606#5:314\n1617#5:315\n1924#5,2:316\n1586#5:337\n1661#5,3:338\n1926#5:342\n1618#5:343\n777#5:344\n873#5,2:345\n*S KotlinDebug\n*F\n+ 1 ProviderManager.kt\ncom/cncverse/ProviderManager$fetchLiveEvents$2\n*L\n233#1:295\n233#1:297,15\n238#1:318\n238#1:320,15\n233#1:296\n238#1:319\n236#1:341\n233#1:312\n233#1:313\n238#1:335\n238#1:336\n236#1:314\n236#1:315\n236#1:316,2\n261#1:337\n261#1:338,3\n236#1:342\n236#1:343\n276#1:344\n276#1:345,2\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends LiveEventData>>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<LiveEventData>> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:120:0x029b  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x02a9  */
        /* JADX WARN: Removed duplicated region for block: B:131:0x02bd  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x031b  */
        /* JADX WARN: Removed duplicated region for block: B:136:0x031e  */
        /* JADX WARN: Removed duplicated region for block: B:139:0x0326 A[Catch: Exception -> 0x038b, TryCatch #4 {Exception -> 0x038b, blocks: (B:126:0x02af, B:133:0x02c3, B:137:0x0320, B:139:0x0326, B:140:0x033d, B:142:0x0343, B:143:0x036c, B:145:0x0381, B:144:0x0379), top: B:182:0x02af }] */
        /* JADX WARN: Removed duplicated region for block: B:144:0x0379 A[Catch: Exception -> 0x038b, TryCatch #4 {Exception -> 0x038b, blocks: (B:126:0x02af, B:133:0x02c3, B:137:0x0320, B:139:0x0326, B:140:0x033d, B:142:0x0343, B:143:0x036c, B:145:0x0381, B:144:0x0379), top: B:182:0x02af }] */
        /* JADX WARN: Removed duplicated region for block: B:157:0x03c9 A[Catch: Exception -> 0x001e, TryCatch #12 {Exception -> 0x001e, blocks: (B:6:0x0018, B:14:0x0033, B:16:0x0070, B:18:0x007d, B:24:0x0089, B:26:0x00bf, B:32:0x00cb, B:43:0x0123, B:51:0x0152, B:54:0x0159, B:64:0x018b, B:65:0x01a4, B:67:0x01aa, B:69:0x01b2, B:70:0x01b5, B:157:0x03c9, B:154:0x039a, B:159:0x03d9, B:160:0x03f6, B:162:0x03fc, B:167:0x0410, B:169:0x0416, B:63:0x0174, B:62:0x016e, B:50:0x0147, B:42:0x0119, B:171:0x041b, B:172:0x0427, B:173:0x0433, B:11:0x0025, B:47:0x012b, B:57:0x015f), top: B:196:0x000d, inners: #6, #16 }] */
        /* JADX WARN: Removed duplicated region for block: B:209:0x03cd A[SYNTHETIC] */
        /* JADX WARN: Unreachable blocks removed: 2, instructions: 5 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r54) {
            /*
                Method dump skipped, instruction units count: 1158
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cncverse.ProviderManager.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Nullable
    public final Object fetchLiveEvents(@NotNull Continuation<? super List<LiveEventData>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(null), continuation);
    }
}
