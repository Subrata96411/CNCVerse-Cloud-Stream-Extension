package com.cncverse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.module.kotlin.ExtensionsKt;
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainActivityKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: CineTvProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nCineTvProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CineTvProvider.kt\ncom/cncverse/CineTvProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,996:1\n1#2:997\n1#2:1011\n1#2:1026\n1#2:1041\n1#2:1056\n1#2:1071\n221#3,2:998\n221#3,2:1013\n221#3,2:1028\n221#3,2:1043\n221#3,2:1058\n116#4:1000\n54#4:1001\n117#4:1002\n61#4,8:1003\n71#4:1012\n116#4:1015\n54#4:1016\n117#4:1017\n61#4,8:1018\n71#4:1027\n116#4:1030\n54#4:1031\n117#4:1032\n61#4,8:1033\n71#4:1042\n116#4:1045\n54#4:1046\n117#4:1047\n61#4,8:1048\n71#4:1057\n116#4:1060\n54#4:1061\n117#4:1062\n61#4,8:1063\n71#4:1072\n1915#5,2:1073\n1915#5,2:1075\n1586#5:1077\n1661#5,3:1078\n1586#5:1081\n1661#5,3:1082\n1586#5:1085\n1661#5,3:1086\n*S KotlinDebug\n*F\n+ 1 CineTvProvider.kt\ncom/cncverse/CineTvProvider\n*L\n350#1:1011\n422#1:1026\n457#1:1041\n493#1:1056\n533#1:1071\n332#1:998,2\n411#1:1013,2\n446#1:1028,2\n482#1:1043,2\n522#1:1058,2\n350#1:1000\n350#1:1001\n350#1:1002\n350#1:1003,8\n350#1:1012\n422#1:1015\n422#1:1016\n422#1:1017\n422#1:1018,8\n422#1:1027\n457#1:1030\n457#1:1031\n457#1:1032\n457#1:1033,8\n457#1:1042\n493#1:1045\n493#1:1046\n493#1:1047\n493#1:1048,8\n493#1:1057\n533#1:1060\n533#1:1061\n533#1:1062\n533#1:1063,8\n533#1:1072\n557#1:1073,2\n600#1:1075,2\n652#1:1077\n652#1:1078,3\n653#1:1081\n653#1:1082,3\n675#1:1085\n675#1:1086,3\n*E\n"})
public final class CineTvProvider extends MainAPI {

    @NotNull

    @Nullable
    private static Context context;

    @Nullable
    private String token;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String SECRET_KEY_ENCRYPTED = "MxASAkl/yHTGg+/Tw1R7u96nGqkWsOZ2";

    @NotNull
    private static final String DES_KEY = "dsawdf634eebGFHITR5UT9kS0";

    @NotNull
    private static final String DES_IV = "32456738";

    @NotNull
    private static final String AES_KEY = "0123456789123456";

    @NotNull
    private static final String AES_IV = "2015030120123456";

    @NotNull
    private static final String WS_SECRET = "00b5f05c40b4f1d91dbc9b3fd8a059ef";

    @NotNull
    private String mainUrl = "https://filmin.ajfysu.com";

    @NotNull
    private String name = "CineTv";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    @NotNull
    private final SecureRandom random = new SecureRandom();

    @NotNull
    private final Map<String, List<String>> brandModels = MapsKt.mapOf(new Pair[]{TuplesKt.to("Samsung", CollectionsKt.listOf(new String[]{"SM-S918B", "SM-A528B", "SM-M336B"})), TuplesKt.to("Xiaomi", CollectionsKt.listOf(new String[]{"2201117TI", "M2012K11AI", "Redmi Note 11"})), TuplesKt.to("OnePlus", CollectionsKt.listOf(new String[]{"LE2111", "CPH2449", "IN2023"})), TuplesKt.to("Google", CollectionsKt.listOf(new String[]{"Pixel 6", "Pixel 7", "Pixel 8"})), TuplesKt.to("Realme", CollectionsKt.listOf(new String[]{"RMX3085", "RMX3360", "RMX3551"}))});

    @NotNull
    private final String deviceId = generateDeviceId();

    @NotNull
    private final BrandModel brandModel = randomBrandModel();

    @NotNull
    private final String mobMfr = this.brandModel.getBrand();

    @NotNull
    private final String mobModel = this.brandModel.getModel();

    @NotNull
    private final String gaid = "";

    @NotNull
    private final ObjectMapper mapper = ExtensionsKt.jacksonObjectMapper();

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("1", "Recommended")});

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$getHeaders$1, reason: invalid class name */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0}, l = {365}, m = "getHeaders", n = {"curTime", "timestamp"}, nl = {369}, s = {"L$0", "L$1"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.getHeaders(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 1, 1}, l = {550, 552}, m = "getMainPage", n = {"request", "page", "request", "page"}, nl = {552, 549}, s = {"L$0", "I$0", "L$0", "I$0"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$getVodInfo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 0, 0}, l = {506}, m = "getVodInfo", n = {"vodId", "url", "curTime", "audioType"}, nl = {509}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.getVodInfo(null, 0, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {644, 664, 683}, m = "load", n = {"url", "parts", "vodId", "typePid", "url", "parts", "vodId", "vodInfoResponse", "vodInfo", "name", "posterUrl", "year", "plot", "score", "tags", "actors", "movieData", "typePid", "url", "parts", "vodId", "vodInfoResponse", "vodInfo", "name", "posterUrl", "year", "plot", "score", "tags", "actors", "episodes", "typePid"}, nl = {645, 675, 692}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {726, 739}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "parts", "vodId", "isCasting", "collection", "data", "subtitleCallback", "callback", "parts", "vodId", "vodInfoResponse", "vodInfo", "episode", "videoUrl", "signedUrl", "isCasting", "collection"}, nl = {727, 738}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0}, l = {595}, m = "search", n = {"query"}, nl = {596}, s = {"L$0"}, v = 2)
    static final class C00051 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00051(Continuation<? super C00051> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$searchRecommend$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 0}, l = {401}, m = "searchRecommend", n = {"url", "curTime", "pageNumber"}, nl = {403}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C00061 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00061(Continuation<? super C00061> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.searchRecommend(0, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$searchVod$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 0, 0}, l = {471}, m = "searchVod", n = {"keyword", "url", "curTime", "pageNumber"}, nl = {473}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C00071 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00071(Continuation<? super C00071> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.searchVod(null, 0, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$topicVodList$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider", f = "CineTvProvider.kt", i = {0, 0, 0, 0}, l = {435}, m = "topicVodList", n = {"url", "curTime", "topicId", "pageNumber"}, nl = {437}, s = {"L$0", "L$1", "I$0", "I$1"}, v = 2)
    static final class C00081 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00081(Continuation<? super C00081> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CineTvProvider.this.topicVodList(0, 0, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/cncverse/CineTvProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "SECRET_KEY_ENCRYPTED", "", "DES_KEY", "DES_IV", "AES_KEY", "AES_IV", "WS_SECRET", "OMG10", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return CineTvProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            CineTvProvider.context = context;
        }
    }

    @NotNull
    public String getMainUrl() {
        return this.mainUrl;
    }

    public void setMainUrl(@NotNull String str) {
        this.mainUrl = str;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void setName(@NotNull String str) {
        this.name = str;
    }

    public boolean getHasMainPage() {
        return this.hasMainPage;
    }

    @NotNull
    public String getLang() {
        return this.lang;
    }

    public void setLang(@NotNull String str) {
        this.lang = str;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    private final String generateDeviceId() {
        byte[] bytes = new byte[16];
        this.random.nextBytes(bytes);
        return ArraysKt.joinToString$default(bytes, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.cncverse.CineTvProvider$$ExternalSyntheticLambda2
            public final Object invoke(Object obj) {
                return CineTvProvider.generateDeviceId$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence generateDeviceId$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cncverse/CineTvProvider$BrandModel;", "", "brand", "", "model", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getBrand", "()Ljava/lang/String;", "getModel", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BrandModel {

        @NotNull
        private final String brand;

        @NotNull
        private final String model;

        public static /* synthetic */ BrandModel copy$default(BrandModel brandModel, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = brandModel.brand;
            }
            if ((i & 2) != 0) {
                str2 = brandModel.model;
            }
            return brandModel.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBrand() {
            return this.brand;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getModel() {
            return this.model;
        }

        @NotNull
        public final BrandModel copy(@NotNull String brand, @NotNull String model) {
            return new BrandModel(brand, model);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BrandModel)) {
                return false;
            }
            BrandModel brandModel = (BrandModel) other;
            return Intrinsics.areEqual(this.brand, brandModel.brand) && Intrinsics.areEqual(this.model, brandModel.model);
        }

        public int hashCode() {
            return (this.brand.hashCode() * 31) + this.model.hashCode();
        }

        @NotNull
        public String toString() {
            return "BrandModel(brand=" + this.brand + ", model=" + this.model + ')';
        }

        public BrandModel(@NotNull String brand, @NotNull String model) {
            this.brand = brand;
            this.model = model;
        }

        @NotNull
        public final String getBrand() {
            return this.brand;
        }

        @NotNull
        public final String getModel() {
            return this.model;
        }
    }

    private final BrandModel randomBrandModel() {
        String brand = (String) CollectionsKt.random(this.brandModels.keySet(), Random.Default);
        List<String> list = this.brandModels.get(brand);
        Intrinsics.checkNotNull(list);
        String model = (String) CollectionsKt.random(list, Random.Default);
        return new BrandModel(brand, model);
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B©\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00103\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010$J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J°\u0001\u00107\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\u000b\u001a\u00020\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u00108J\u0014\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010<\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010=\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0016\u0010\u000b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0016R\u001a\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u001a\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0018R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018¨\u0006>"}, d2 = {"Lcom/cncverse/CineTvProvider$VodItem;", "", "id", "", "vodName", "", "vodPic", "vodYear", "vodActor", "vodDirector", "vodBlurb", "typePid", "vodTotal", "vodSerial", "vodDoubanScore", "", "vodEn", "audioLanguageTag", "vodArea", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getVodName", "()Ljava/lang/String;", "getVodPic", "getVodYear", "getVodActor", "getVodDirector", "getVodBlurb", "getTypePid", "getVodTotal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getVodSerial", "getVodDoubanScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getVodEn", "getAudioLanguageTag", "getVodArea", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/cncverse/CineTvProvider$VodItem;", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VodItem {

        @JsonProperty("audio_language_tag")
        @Nullable
        private final String audioLanguageTag;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("type_pid")
        private final int typePid;

        @JsonProperty("vod_actor")
        @Nullable
        private final String vodActor;

        @JsonProperty("vod_area")
        @Nullable
        private final String vodArea;

        @JsonProperty("vod_blurb")
        @Nullable
        private final String vodBlurb;

        @JsonProperty("vod_director")
        @Nullable
        private final String vodDirector;

        @JsonProperty("vod_douban_score")
        @Nullable
        private final Double vodDoubanScore;

        @JsonProperty("vod_en")
        @Nullable
        private final String vodEn;

        @JsonProperty("vod_name")
        @NotNull
        private final String vodName;

        @JsonProperty("vod_pic")
        @Nullable
        private final String vodPic;

        @JsonProperty("vod_serial")
        @Nullable
        private final Integer vodSerial;

        @JsonProperty("vod_total")
        @Nullable
        private final Integer vodTotal;

        @JsonProperty("vod_year")
        @Nullable
        private final String vodYear;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getVodSerial() {
            return this.vodSerial;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Double getVodDoubanScore() {
            return this.vodDoubanScore;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getVodEn() {
            return this.vodEn;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getAudioLanguageTag() {
            return this.audioLanguageTag;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getVodArea() {
            return this.vodArea;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVodName() {
            return this.vodName;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVodPic() {
            return this.vodPic;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getVodYear() {
            return this.vodYear;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getVodActor() {
            return this.vodActor;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getVodDirector() {
            return this.vodDirector;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getVodBlurb() {
            return this.vodBlurb;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getTypePid() {
            return this.typePid;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getVodTotal() {
            return this.vodTotal;
        }

        @NotNull
        public final VodItem copy(@JsonProperty("id") int id, @JsonProperty("vod_name") @NotNull String vodName, @JsonProperty("vod_pic") @Nullable String vodPic, @JsonProperty("vod_year") @Nullable String vodYear, @JsonProperty("vod_actor") @Nullable String vodActor, @JsonProperty("vod_director") @Nullable String vodDirector, @JsonProperty("vod_blurb") @Nullable String vodBlurb, @JsonProperty("type_pid") int typePid, @JsonProperty("vod_total") @Nullable Integer vodTotal, @JsonProperty("vod_serial") @Nullable Integer vodSerial, @JsonProperty("vod_douban_score") @Nullable Double vodDoubanScore, @JsonProperty("vod_en") @Nullable String vodEn, @JsonProperty("audio_language_tag") @Nullable String audioLanguageTag, @JsonProperty("vod_area") @Nullable String vodArea) {
            return new VodItem(id, vodName, vodPic, vodYear, vodActor, vodDirector, vodBlurb, typePid, vodTotal, vodSerial, vodDoubanScore, vodEn, audioLanguageTag, vodArea);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VodItem)) {
                return false;
            }
            VodItem vodItem = (VodItem) other;
            return this.id == vodItem.id && Intrinsics.areEqual(this.vodName, vodItem.vodName) && Intrinsics.areEqual(this.vodPic, vodItem.vodPic) && Intrinsics.areEqual(this.vodYear, vodItem.vodYear) && Intrinsics.areEqual(this.vodActor, vodItem.vodActor) && Intrinsics.areEqual(this.vodDirector, vodItem.vodDirector) && Intrinsics.areEqual(this.vodBlurb, vodItem.vodBlurb) && this.typePid == vodItem.typePid && Intrinsics.areEqual(this.vodTotal, vodItem.vodTotal) && Intrinsics.areEqual(this.vodSerial, vodItem.vodSerial) && Intrinsics.areEqual(this.vodDoubanScore, vodItem.vodDoubanScore) && Intrinsics.areEqual(this.vodEn, vodItem.vodEn) && Intrinsics.areEqual(this.audioLanguageTag, vodItem.audioLanguageTag) && Intrinsics.areEqual(this.vodArea, vodItem.vodArea);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((this.id * 31) + this.vodName.hashCode()) * 31) + (this.vodPic == null ? 0 : this.vodPic.hashCode())) * 31) + (this.vodYear == null ? 0 : this.vodYear.hashCode())) * 31) + (this.vodActor == null ? 0 : this.vodActor.hashCode())) * 31) + (this.vodDirector == null ? 0 : this.vodDirector.hashCode())) * 31) + (this.vodBlurb == null ? 0 : this.vodBlurb.hashCode())) * 31) + this.typePid) * 31) + (this.vodTotal == null ? 0 : this.vodTotal.hashCode())) * 31) + (this.vodSerial == null ? 0 : this.vodSerial.hashCode())) * 31) + (this.vodDoubanScore == null ? 0 : this.vodDoubanScore.hashCode())) * 31) + (this.vodEn == null ? 0 : this.vodEn.hashCode())) * 31) + (this.audioLanguageTag == null ? 0 : this.audioLanguageTag.hashCode())) * 31) + (this.vodArea != null ? this.vodArea.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("VodItem(id=").append(this.id).append(", vodName=").append(this.vodName).append(", vodPic=").append(this.vodPic).append(", vodYear=").append(this.vodYear).append(", vodActor=").append(this.vodActor).append(", vodDirector=").append(this.vodDirector).append(", vodBlurb=").append(this.vodBlurb).append(", typePid=").append(this.typePid).append(", vodTotal=").append(this.vodTotal).append(", vodSerial=").append(this.vodSerial).append(", vodDoubanScore=").append(this.vodDoubanScore).append(", vodEn=");
            sb.append(this.vodEn).append(", audioLanguageTag=").append(this.audioLanguageTag).append(", vodArea=").append(this.vodArea).append(')');
            return sb.toString();
        }

        public VodItem(@JsonProperty("id") int id, @JsonProperty("vod_name") @NotNull String vodName, @JsonProperty("vod_pic") @Nullable String vodPic, @JsonProperty("vod_year") @Nullable String vodYear, @JsonProperty("vod_actor") @Nullable String vodActor, @JsonProperty("vod_director") @Nullable String vodDirector, @JsonProperty("vod_blurb") @Nullable String vodBlurb, @JsonProperty("type_pid") int typePid, @JsonProperty("vod_total") @Nullable Integer vodTotal, @JsonProperty("vod_serial") @Nullable Integer vodSerial, @JsonProperty("vod_douban_score") @Nullable Double vodDoubanScore, @JsonProperty("vod_en") @Nullable String vodEn, @JsonProperty("audio_language_tag") @Nullable String audioLanguageTag, @JsonProperty("vod_area") @Nullable String vodArea) {
            this.id = id;
            this.vodName = vodName;
            this.vodPic = vodPic;
            this.vodYear = vodYear;
            this.vodActor = vodActor;
            this.vodDirector = vodDirector;
            this.vodBlurb = vodBlurb;
            this.typePid = typePid;
            this.vodTotal = vodTotal;
            this.vodSerial = vodSerial;
            this.vodDoubanScore = vodDoubanScore;
            this.vodEn = vodEn;
            this.audioLanguageTag = audioLanguageTag;
            this.vodArea = vodArea;
        }

        public final int getId() {
            return this.id;
        }

        @NotNull
        public final String getVodName() {
            return this.vodName;
        }

        @Nullable
        public final String getVodPic() {
            return this.vodPic;
        }

        @Nullable
        public final String getVodYear() {
            return this.vodYear;
        }

        @Nullable
        public final String getVodActor() {
            return this.vodActor;
        }

        @Nullable
        public final String getVodDirector() {
            return this.vodDirector;
        }

        @Nullable
        public final String getVodBlurb() {
            return this.vodBlurb;
        }

        public final int getTypePid() {
            return this.typePid;
        }

        @Nullable
        public final Integer getVodTotal() {
            return this.vodTotal;
        }

        @Nullable
        public final Integer getVodSerial() {
            return this.vodSerial;
        }

        @Nullable
        public final Double getVodDoubanScore() {
            return this.vodDoubanScore;
        }

        @Nullable
        public final String getVodEn() {
            return this.vodEn;
        }

        @Nullable
        public final String getAudioLanguageTag() {
            return this.audioLanguageTag;
        }

        @Nullable
        public final String getVodArea() {
            return this.vodArea;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0001\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u0010\b\u0003\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/cncverse/CineTvProvider$ApiResponse;", "", "code", "", "message", "", "result", "", "Lcom/cncverse/CineTvProvider$VodItem;", "<init>", "(ILjava/lang/String;Ljava/util/List;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "getResult", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ApiResponse {

        @JsonProperty("code")
        private final int code;

        @JsonProperty("message")
        @NotNull
        private final String message;

        @JsonProperty("result")
        @Nullable
        private final List<VodItem> result;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ApiResponse copy$default(ApiResponse apiResponse, int i, String str, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = apiResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = apiResponse.message;
            }
            if ((i2 & 4) != 0) {
                list = apiResponse.result;
            }
            return apiResponse.copy(i, str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final List<VodItem> component3() {
            return this.result;
        }

        @NotNull
        public final ApiResponse copy(@JsonProperty("code") int code, @JsonProperty("message") @NotNull String message, @JsonProperty("result") @Nullable List<VodItem> result) {
            return new ApiResponse(code, message, result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ApiResponse)) {
                return false;
            }
            ApiResponse apiResponse = (ApiResponse) other;
            return this.code == apiResponse.code && Intrinsics.areEqual(this.message, apiResponse.message) && Intrinsics.areEqual(this.result, apiResponse.result);
        }

        public int hashCode() {
            return (((this.code * 31) + this.message.hashCode()) * 31) + (this.result == null ? 0 : this.result.hashCode());
        }

        @NotNull
        public String toString() {
            return "ApiResponse(code=" + this.code + ", message=" + this.message + ", result=" + this.result + ')';
        }

        public ApiResponse(@JsonProperty("code") int code, @JsonProperty("message") @NotNull String message, @JsonProperty("result") @Nullable List<VodItem> list) {
            this.code = code;
            this.message = message;
            this.result = list;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final List<VodItem> getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0001\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003JD\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0003\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0006HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/cncverse/CineTvProvider$TopicResult;", "", "id", "", "typeId", "name", "", "vodList", "", "Lcom/cncverse/CineTvProvider$VodItem;", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTypeId", "getName", "()Ljava/lang/String;", "getVodList", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;)Lcom/cncverse/CineTvProvider$TopicResult;", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TopicResult {

        @JsonProperty("id")
        @Nullable
        private final Integer id;

        @JsonProperty("name")
        @Nullable
        private final String name;

        @JsonProperty("type_id")
        @Nullable
        private final Integer typeId;

        @JsonProperty("vod_list")
        @Nullable
        private final List<VodItem> vodList;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ TopicResult copy$default(TopicResult topicResult, Integer num, Integer num2, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                num = topicResult.id;
            }
            if ((i & 2) != 0) {
                num2 = topicResult.typeId;
            }
            if ((i & 4) != 0) {
                str = topicResult.name;
            }
            if ((i & 8) != 0) {
                list = topicResult.vodList;
            }
            return topicResult.copy(num, num2, str, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTypeId() {
            return this.typeId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final List<VodItem> component4() {
            return this.vodList;
        }

        @NotNull
        public final TopicResult copy(@JsonProperty("id") @Nullable Integer id, @JsonProperty("type_id") @Nullable Integer typeId, @JsonProperty("name") @Nullable String name, @JsonProperty("vod_list") @Nullable List<VodItem> vodList) {
            return new TopicResult(id, typeId, name, vodList);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TopicResult)) {
                return false;
            }
            TopicResult topicResult = (TopicResult) other;
            return Intrinsics.areEqual(this.id, topicResult.id) && Intrinsics.areEqual(this.typeId, topicResult.typeId) && Intrinsics.areEqual(this.name, topicResult.name) && Intrinsics.areEqual(this.vodList, topicResult.vodList);
        }

        public int hashCode() {
            return ((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.typeId == null ? 0 : this.typeId.hashCode())) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.vodList != null ? this.vodList.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "TopicResult(id=" + this.id + ", typeId=" + this.typeId + ", name=" + this.name + ", vodList=" + this.vodList + ')';
        }

        public TopicResult(@JsonProperty("id") @Nullable Integer id, @JsonProperty("type_id") @Nullable Integer typeId, @JsonProperty("name") @Nullable String name, @JsonProperty("vod_list") @Nullable List<VodItem> list) {
            this.id = id;
            this.typeId = typeId;
            this.name = name;
            this.vodList = list;
        }

        @Nullable
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        public final Integer getTypeId() {
            return this.typeId;
        }

        @Nullable
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final List<VodItem> getVodList() {
            return this.vodList;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/CineTvProvider$TopicApiResponse;", "", "code", "", "message", "", "result", "Lcom/cncverse/CineTvProvider$TopicResult;", "<init>", "(ILjava/lang/String;Lcom/cncverse/CineTvProvider$TopicResult;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "getResult", "()Lcom/cncverse/CineTvProvider$TopicResult;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TopicApiResponse {

        @JsonProperty("code")
        private final int code;

        @JsonProperty("message")
        @NotNull
        private final String message;

        @JsonProperty("result")
        @Nullable
        private final TopicResult result;

        public static /* synthetic */ TopicApiResponse copy$default(TopicApiResponse topicApiResponse, int i, String str, TopicResult topicResult, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = topicApiResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = topicApiResponse.message;
            }
            if ((i2 & 4) != 0) {
                topicResult = topicApiResponse.result;
            }
            return topicApiResponse.copy(i, str, topicResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final TopicResult getResult() {
            return this.result;
        }

        @NotNull
        public final TopicApiResponse copy(@JsonProperty("code") int code, @JsonProperty("message") @NotNull String message, @JsonProperty("result") @Nullable TopicResult result) {
            return new TopicApiResponse(code, message, result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TopicApiResponse)) {
                return false;
            }
            TopicApiResponse topicApiResponse = (TopicApiResponse) other;
            return this.code == topicApiResponse.code && Intrinsics.areEqual(this.message, topicApiResponse.message) && Intrinsics.areEqual(this.result, topicApiResponse.result);
        }

        public int hashCode() {
            return (((this.code * 31) + this.message.hashCode()) * 31) + (this.result == null ? 0 : this.result.hashCode());
        }

        @NotNull
        public String toString() {
            return "TopicApiResponse(code=" + this.code + ", message=" + this.message + ", result=" + this.result + ')';
        }

        public TopicApiResponse(@JsonProperty("code") int code, @JsonProperty("message") @NotNull String message, @JsonProperty("result") @Nullable TopicResult result) {
            this.code = code;
            this.message = message;
            this.result = result;
        }

        public final int getCode() {
            return this.code;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final TopicResult getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/cncverse/CineTvProvider$InitResponse;", "", "code", "", "result", "Lcom/cncverse/CineTvProvider$InitResult;", "<init>", "(ILcom/cncverse/CineTvProvider$InitResult;)V", "getCode", "()I", "getResult", "()Lcom/cncverse/CineTvProvider$InitResult;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class InitResponse {

        @JsonProperty("code")
        private final int code;

        @JsonProperty("result")
        @Nullable
        private final InitResult result;

        public static /* synthetic */ InitResponse copy$default(InitResponse initResponse, int i, InitResult initResult, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = initResponse.code;
            }
            if ((i2 & 2) != 0) {
                initResult = initResponse.result;
            }
            return initResponse.copy(i, initResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final InitResult getResult() {
            return this.result;
        }

        @NotNull
        public final InitResponse copy(@JsonProperty("code") int code, @JsonProperty("result") @Nullable InitResult result) {
            return new InitResponse(code, result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof InitResponse)) {
                return false;
            }
            InitResponse initResponse = (InitResponse) other;
            return this.code == initResponse.code && Intrinsics.areEqual(this.result, initResponse.result);
        }

        public int hashCode() {
            return (this.code * 31) + (this.result == null ? 0 : this.result.hashCode());
        }

        @NotNull
        public String toString() {
            return "InitResponse(code=" + this.code + ", result=" + this.result + ')';
        }

        public InitResponse(@JsonProperty("code") int code, @JsonProperty("result") @Nullable InitResult result) {
            this.code = code;
            this.result = result;
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final InitResult getResult() {
            return this.result;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/cncverse/CineTvProvider$InitResult;", "", "userInfo", "Lcom/cncverse/CineTvProvider$UserInfo;", "<init>", "(Lcom/cncverse/CineTvProvider$UserInfo;)V", "getUserInfo", "()Lcom/cncverse/CineTvProvider$UserInfo;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class InitResult {

        @JsonProperty("user_info")
        @Nullable
        private final UserInfo userInfo;

        public static /* synthetic */ InitResult copy$default(InitResult initResult, UserInfo userInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                userInfo = initResult.userInfo;
            }
            return initResult.copy(userInfo);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final UserInfo getUserInfo() {
            return this.userInfo;
        }

        @NotNull
        public final InitResult copy(@JsonProperty("user_info") @Nullable UserInfo userInfo) {
            return new InitResult(userInfo);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof InitResult) && Intrinsics.areEqual(this.userInfo, ((InitResult) other).userInfo);
        }

        public int hashCode() {
            if (this.userInfo == null) {
                return 0;
            }
            return this.userInfo.hashCode();
        }

        @NotNull
        public String toString() {
            return "InitResult(userInfo=" + this.userInfo + ')';
        }

        public InitResult(@JsonProperty("user_info") @Nullable UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        @Nullable
        public final UserInfo getUserInfo() {
            return this.userInfo;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/cncverse/CineTvProvider$UserInfo;", "", "token", "", "<init>", "(Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class UserInfo {

        @JsonProperty("token")
        @Nullable
        private final String token;

        public static /* synthetic */ UserInfo copy$default(UserInfo userInfo, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = userInfo.token;
            }
            return userInfo.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getToken() {
            return this.token;
        }

        @NotNull
        public final UserInfo copy(@JsonProperty("token") @Nullable String token) {
            return new UserInfo(token);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof UserInfo) && Intrinsics.areEqual(this.token, ((UserInfo) other).token);
        }

        public int hashCode() {
            if (this.token == null) {
                return 0;
            }
            return this.token.hashCode();
        }

        @NotNull
        public String toString() {
            return "UserInfo(token=" + this.token + ')';
        }

        public UserInfo(@JsonProperty("token") @Nullable String token) {
            this.token = token;
        }

        @Nullable
        public final String getToken() {
            return this.token;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJn\u0010 \u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010!J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0016\u0010\u000fR\u001a\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0017\u0010\u000fR\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000b\u0010\u000f¨\u0006'"}, d2 = {"Lcom/cncverse/CineTvProvider$VodCollection;", "", "id", "", "title", "", "vodUrl", "downUrl", "duration", "vodDuration", "collection", "isP2p", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "()Ljava/lang/String;", "getVodUrl", "getDownUrl", "getDuration", "getVodDuration", "getCollection", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/cncverse/CineTvProvider$VodCollection;", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VodCollection {

        @JsonProperty("collection")
        @Nullable
        private final Integer collection;

        @JsonProperty("down_url")
        @Nullable
        private final String downUrl;

        @JsonProperty("duration")
        @Nullable
        private final String duration;

        @JsonProperty("id")
        @Nullable
        private final Integer id;

        @JsonProperty("is_p2p")
        @Nullable
        private final Integer isP2p;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("vod_duration")
        @Nullable
        private final Integer vodDuration;

        @JsonProperty("vod_url")
        @Nullable
        private final String vodUrl;

        public static /* synthetic */ VodCollection copy$default(VodCollection vodCollection, Integer num, String str, String str2, String str3, String str4, Integer num2, Integer num3, Integer num4, int i, Object obj) {
            if ((i & 1) != 0) {
                num = vodCollection.id;
            }
            if ((i & 2) != 0) {
                str = vodCollection.title;
            }
            if ((i & 4) != 0) {
                str2 = vodCollection.vodUrl;
            }
            if ((i & 8) != 0) {
                str3 = vodCollection.downUrl;
            }
            if ((i & 16) != 0) {
                str4 = vodCollection.duration;
            }
            if ((i & 32) != 0) {
                num2 = vodCollection.vodDuration;
            }
            if ((i & 64) != 0) {
                num3 = vodCollection.collection;
            }
            if ((i & 128) != 0) {
                num4 = vodCollection.isP2p;
            }
            Integer num5 = num3;
            Integer num6 = num4;
            String str5 = str4;
            Integer num7 = num2;
            return vodCollection.copy(num, str, str2, str3, str5, num7, num5, num6);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVodUrl() {
            return this.vodUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getDownUrl() {
            return this.downUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDuration() {
            return this.duration;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getVodDuration() {
            return this.vodDuration;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getCollection() {
            return this.collection;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getIsP2p() {
            return this.isP2p;
        }

        @NotNull
        public final VodCollection copy(@JsonProperty("id") @Nullable Integer id, @JsonProperty("title") @Nullable String title, @JsonProperty("vod_url") @Nullable String vodUrl, @JsonProperty("down_url") @Nullable String downUrl, @JsonProperty("duration") @Nullable String duration, @JsonProperty("vod_duration") @Nullable Integer vodDuration, @JsonProperty("collection") @Nullable Integer collection, @JsonProperty("is_p2p") @Nullable Integer isP2p) {
            return new VodCollection(id, title, vodUrl, downUrl, duration, vodDuration, collection, isP2p);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VodCollection)) {
                return false;
            }
            VodCollection vodCollection = (VodCollection) other;
            return Intrinsics.areEqual(this.id, vodCollection.id) && Intrinsics.areEqual(this.title, vodCollection.title) && Intrinsics.areEqual(this.vodUrl, vodCollection.vodUrl) && Intrinsics.areEqual(this.downUrl, vodCollection.downUrl) && Intrinsics.areEqual(this.duration, vodCollection.duration) && Intrinsics.areEqual(this.vodDuration, vodCollection.vodDuration) && Intrinsics.areEqual(this.collection, vodCollection.collection) && Intrinsics.areEqual(this.isP2p, vodCollection.isP2p);
        }

        public int hashCode() {
            return ((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.vodUrl == null ? 0 : this.vodUrl.hashCode())) * 31) + (this.downUrl == null ? 0 : this.downUrl.hashCode())) * 31) + (this.duration == null ? 0 : this.duration.hashCode())) * 31) + (this.vodDuration == null ? 0 : this.vodDuration.hashCode())) * 31) + (this.collection == null ? 0 : this.collection.hashCode())) * 31) + (this.isP2p != null ? this.isP2p.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VodCollection(id=" + this.id + ", title=" + this.title + ", vodUrl=" + this.vodUrl + ", downUrl=" + this.downUrl + ", duration=" + this.duration + ", vodDuration=" + this.vodDuration + ", collection=" + this.collection + ", isP2p=" + this.isP2p + ')';
        }

        public VodCollection(@JsonProperty("id") @Nullable Integer id, @JsonProperty("title") @Nullable String title, @JsonProperty("vod_url") @Nullable String vodUrl, @JsonProperty("down_url") @Nullable String downUrl, @JsonProperty("duration") @Nullable String duration, @JsonProperty("vod_duration") @Nullable Integer vodDuration, @JsonProperty("collection") @Nullable Integer collection, @JsonProperty("is_p2p") @Nullable Integer isP2p) {
            this.id = id;
            this.title = title;
            this.vodUrl = vodUrl;
            this.downUrl = downUrl;
            this.duration = duration;
            this.vodDuration = vodDuration;
            this.collection = collection;
            this.isP2p = isP2p;
        }

        @Nullable
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getVodUrl() {
            return this.vodUrl;
        }

        @Nullable
        public final String getDownUrl() {
            return this.downUrl;
        }

        @Nullable
        public final String getDuration() {
            return this.duration;
        }

        @Nullable
        public final Integer getVodDuration() {
            return this.vodDuration;
        }

        @Nullable
        public final Integer getCollection() {
            return this.collection;
        }

        @Nullable
        public final Integer isP2p() {
            return this.isP2p;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/cncverse/CineTvProvider$AudioTypeOption;", "", "type", "", "typeName", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTypeName", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/cncverse/CineTvProvider$AudioTypeOption;", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AudioTypeOption {

        @JsonProperty("type")
        @Nullable
        private final Integer type;

        @JsonProperty("type_name")
        @Nullable
        private final String typeName;

        public static /* synthetic */ AudioTypeOption copy$default(AudioTypeOption audioTypeOption, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                num = audioTypeOption.type;
            }
            if ((i & 2) != 0) {
                str = audioTypeOption.typeName;
            }
            return audioTypeOption.copy(num, str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTypeName() {
            return this.typeName;
        }

        @NotNull
        public final AudioTypeOption copy(@JsonProperty("type") @Nullable Integer type, @JsonProperty("type_name") @Nullable String typeName) {
            return new AudioTypeOption(type, typeName);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AudioTypeOption)) {
                return false;
            }
            AudioTypeOption audioTypeOption = (AudioTypeOption) other;
            return Intrinsics.areEqual(this.type, audioTypeOption.type) && Intrinsics.areEqual(this.typeName, audioTypeOption.typeName);
        }

        public int hashCode() {
            return ((this.type == null ? 0 : this.type.hashCode()) * 31) + (this.typeName != null ? this.typeName.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "AudioTypeOption(type=" + this.type + ", typeName=" + this.typeName + ')';
        }

        public AudioTypeOption(@JsonProperty("type") @Nullable Integer type, @JsonProperty("type_name") @Nullable String typeName) {
            this.type = type;
            this.typeName = typeName;
        }

        @Nullable
        public final Integer getType() {
            return this.type;
        }

        @Nullable
        public final String getTypeName() {
            return this.typeName;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0010J2\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00072\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/CineTvProvider$SeriesInfo;", "", "vodId", "", "series", "", "default", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)V", "getVodId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSeries", "()Ljava/lang/String;", "getDefault", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/cncverse/CineTvProvider$SeriesInfo;", "equals", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SeriesInfo {

        @JsonProperty("default")
        @Nullable
        private final Boolean default;

        @JsonProperty("series")
        @Nullable
        private final String series;

        @JsonProperty("vod_id")
        @Nullable
        private final Integer vodId;

        public static /* synthetic */ SeriesInfo copy$default(SeriesInfo seriesInfo, Integer num, String str, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                num = seriesInfo.vodId;
            }
            if ((i & 2) != 0) {
                str = seriesInfo.series;
            }
            if ((i & 4) != 0) {
                bool = seriesInfo.default;
            }
            return seriesInfo.copy(num, str, bool);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getVodId() {
            return this.vodId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSeries() {
            return this.series;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Boolean getDefault() {
            return this.default;
        }

        @NotNull
        public final SeriesInfo copy(@JsonProperty("vod_id") @Nullable Integer vodId, @JsonProperty("series") @Nullable String series, @JsonProperty("default") @Nullable Boolean bool) {
            return new SeriesInfo(vodId, series, bool);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeriesInfo)) {
                return false;
            }
            SeriesInfo seriesInfo = (SeriesInfo) other;
            return Intrinsics.areEqual(this.vodId, seriesInfo.vodId) && Intrinsics.areEqual(this.series, seriesInfo.series) && Intrinsics.areEqual(this.default, seriesInfo.default);
        }

        public int hashCode() {
            return ((((this.vodId == null ? 0 : this.vodId.hashCode()) * 31) + (this.series == null ? 0 : this.series.hashCode())) * 31) + (this.default != null ? this.default.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "SeriesInfo(vodId=" + this.vodId + ", series=" + this.series + ", default=" + this.default + ')';
        }

        public SeriesInfo(@JsonProperty("vod_id") @Nullable Integer vodId, @JsonProperty("series") @Nullable String series, @JsonProperty("default") @Nullable Boolean bool) {
            this.vodId = vodId;
            this.series = series;
            this.default = bool;
        }

        @Nullable
        public final Integer getVodId() {
            return this.vodId;
        }

        @Nullable
        public final String getSeries() {
            return this.series;
        }

        @Nullable
        public final Boolean getDefault() {
            return this.default;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bå\u0001\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0001\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012\u0012\u0010\b\u0001\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0012\u0012\u0010\b\u0001\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0012\u0012\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001dJ\u0010\u0010=\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010*J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010?\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012HÆ\u0003J\u0011\u0010@\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0012HÆ\u0003J\u0011\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0012HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jì\u0001\u0010D\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0010\b\u0003\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0010\b\u0003\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00122\u0010\b\u0003\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00122\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010EJ\u0014\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010I\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010J\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b&\u0010\u001dR\u001a\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b'\u0010\u001dR\u001a\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b(\u0010\u001dR\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010+\u001a\u0004\b)\u0010*R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u001e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u001e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010.R\u001e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010.R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010 R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010 ¨\u0006K"}, d2 = {"Lcom/cncverse/CineTvProvider$VodInfoResult;", "", "id", "", "vodName", "", "vodPic", "vodYear", "vodActor", "vodDirector", "vodBlurb", "typePid", "vodTotal", "vodSerial", "vodDoubanScore", "", "vodTag", "vodCollection", "", "Lcom/cncverse/CineTvProvider$VodCollection;", "audioTypeOption", "Lcom/cncverse/CineTvProvider$AudioTypeOption;", "seriesInfo", "Lcom/cncverse/CineTvProvider$SeriesInfo;", "audioLanguageTag", "vodArea", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getVodName", "()Ljava/lang/String;", "getVodPic", "getVodYear", "getVodActor", "getVodDirector", "getVodBlurb", "getTypePid", "getVodTotal", "getVodSerial", "getVodDoubanScore", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getVodTag", "getVodCollection", "()Ljava/util/List;", "getAudioTypeOption", "getSeriesInfo", "getAudioLanguageTag", "getVodArea", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/cncverse/CineTvProvider$VodInfoResult;", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VodInfoResult {

        @JsonProperty("audio_language_tag")
        @Nullable
        private final String audioLanguageTag;

        @JsonProperty("audio_type_option")
        @Nullable
        private final List<AudioTypeOption> audioTypeOption;

        @JsonProperty("id")
        @Nullable
        private final Integer id;

        @JsonProperty("series_info")
        @Nullable
        private final List<SeriesInfo> seriesInfo;

        @JsonProperty("type_pid")
        @Nullable
        private final Integer typePid;

        @JsonProperty("vod_actor")
        @Nullable
        private final String vodActor;

        @JsonProperty("vod_area")
        @Nullable
        private final String vodArea;

        @JsonProperty("vod_blurb")
        @Nullable
        private final String vodBlurb;

        @JsonProperty("vod_collection")
        @Nullable
        private final List<VodCollection> vodCollection;

        @JsonProperty("vod_director")
        @Nullable
        private final String vodDirector;

        @JsonProperty("vod_douban_score")
        @Nullable
        private final Double vodDoubanScore;

        @JsonProperty("vod_name")
        @Nullable
        private final String vodName;

        @JsonProperty("vod_pic")
        @Nullable
        private final String vodPic;

        @JsonProperty("vod_serial")
        @Nullable
        private final Integer vodSerial;

        @JsonProperty("vod_tag")
        @Nullable
        private final String vodTag;

        @JsonProperty("vod_total")
        @Nullable
        private final Integer vodTotal;

        @JsonProperty("vod_year")
        @Nullable
        private final String vodYear;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VodInfoResult copy$default(VodInfoResult vodInfoResult, Integer num, String str, String str2, String str3, String str4, String str5, String str6, Integer num2, Integer num3, Integer num4, Double d, String str7, List list, List list2, List list3, String str8, String str9, int i, Object obj) {
            String str10;
            String str11;
            Integer num5;
            VodInfoResult vodInfoResult2;
            List list4;
            String str12;
            String str13;
            String str14;
            String str15;
            String str16;
            String str17;
            Integer num6;
            Integer num7;
            Integer num8;
            Double d2;
            String str18;
            List list5;
            List list6;
            Integer num9 = (i & 1) != 0 ? vodInfoResult.id : num;
            String str19 = (i & 2) != 0 ? vodInfoResult.vodName : str;
            String str20 = (i & 4) != 0 ? vodInfoResult.vodPic : str2;
            String str21 = (i & 8) != 0 ? vodInfoResult.vodYear : str3;
            String str22 = (i & 16) != 0 ? vodInfoResult.vodActor : str4;
            String str23 = (i & 32) != 0 ? vodInfoResult.vodDirector : str5;
            String str24 = (i & 64) != 0 ? vodInfoResult.vodBlurb : str6;
            Integer num10 = (i & 128) != 0 ? vodInfoResult.typePid : num2;
            Integer num11 = (i & 256) != 0 ? vodInfoResult.vodTotal : num3;
            Integer num12 = (i & 512) != 0 ? vodInfoResult.vodSerial : num4;
            Double d3 = (i & 1024) != 0 ? vodInfoResult.vodDoubanScore : d;
            String str25 = (i & 2048) != 0 ? vodInfoResult.vodTag : str7;
            List list7 = (i & 4096) != 0 ? vodInfoResult.vodCollection : list;
            List list8 = (i & 8192) != 0 ? vodInfoResult.audioTypeOption : list2;
            Integer num13 = num9;
            List list9 = (i & 16384) != 0 ? vodInfoResult.seriesInfo : list3;
            String str26 = (i & 32768) != 0 ? vodInfoResult.audioLanguageTag : str8;
            if ((i & 65536) != 0) {
                str11 = str26;
                str10 = vodInfoResult.vodArea;
                list4 = list9;
                str12 = str19;
                str13 = str20;
                str14 = str21;
                str15 = str22;
                str16 = str23;
                str17 = str24;
                num6 = num10;
                num7 = num11;
                num8 = num12;
                d2 = d3;
                str18 = str25;
                list5 = list7;
                list6 = list8;
                num5 = num13;
                vodInfoResult2 = vodInfoResult;
            } else {
                str10 = str9;
                str11 = str26;
                num5 = num13;
                vodInfoResult2 = vodInfoResult;
                list4 = list9;
                str12 = str19;
                str13 = str20;
                str14 = str21;
                str15 = str22;
                str16 = str23;
                str17 = str24;
                num6 = num10;
                num7 = num11;
                num8 = num12;
                d2 = d3;
                str18 = str25;
                list5 = list7;
                list6 = list8;
            }
            return vodInfoResult2.copy(num5, str12, str13, str14, str15, str16, str17, num6, num7, num8, d2, str18, list5, list6, list4, str11, str10);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final Integer getVodSerial() {
            return this.vodSerial;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final Double getVodDoubanScore() {
            return this.vodDoubanScore;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getVodTag() {
            return this.vodTag;
        }

        @Nullable
        public final List<VodCollection> component13() {
            return this.vodCollection;
        }

        @Nullable
        public final List<AudioTypeOption> component14() {
            return this.audioTypeOption;
        }

        @Nullable
        public final List<SeriesInfo> component15() {
            return this.seriesInfo;
        }

        @Nullable
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final String getAudioLanguageTag() {
            return this.audioLanguageTag;
        }

        @Nullable
        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getVodArea() {
            return this.vodArea;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVodName() {
            return this.vodName;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVodPic() {
            return this.vodPic;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getVodYear() {
            return this.vodYear;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getVodActor() {
            return this.vodActor;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getVodDirector() {
            return this.vodDirector;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getVodBlurb() {
            return this.vodBlurb;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getTypePid() {
            return this.typePid;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final Integer getVodTotal() {
            return this.vodTotal;
        }

        @NotNull
        public final VodInfoResult copy(@JsonProperty("id") @Nullable Integer id, @JsonProperty("vod_name") @Nullable String vodName, @JsonProperty("vod_pic") @Nullable String vodPic, @JsonProperty("vod_year") @Nullable String vodYear, @JsonProperty("vod_actor") @Nullable String vodActor, @JsonProperty("vod_director") @Nullable String vodDirector, @JsonProperty("vod_blurb") @Nullable String vodBlurb, @JsonProperty("type_pid") @Nullable Integer typePid, @JsonProperty("vod_total") @Nullable Integer vodTotal, @JsonProperty("vod_serial") @Nullable Integer vodSerial, @JsonProperty("vod_douban_score") @Nullable Double vodDoubanScore, @JsonProperty("vod_tag") @Nullable String vodTag, @JsonProperty("vod_collection") @Nullable List<VodCollection> vodCollection, @JsonProperty("audio_type_option") @Nullable List<AudioTypeOption> audioTypeOption, @JsonProperty("series_info") @Nullable List<SeriesInfo> seriesInfo, @JsonProperty("audio_language_tag") @Nullable String audioLanguageTag, @JsonProperty("vod_area") @Nullable String vodArea) {
            return new VodInfoResult(id, vodName, vodPic, vodYear, vodActor, vodDirector, vodBlurb, typePid, vodTotal, vodSerial, vodDoubanScore, vodTag, vodCollection, audioTypeOption, seriesInfo, audioLanguageTag, vodArea);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VodInfoResult)) {
                return false;
            }
            VodInfoResult vodInfoResult = (VodInfoResult) other;
            return Intrinsics.areEqual(this.id, vodInfoResult.id) && Intrinsics.areEqual(this.vodName, vodInfoResult.vodName) && Intrinsics.areEqual(this.vodPic, vodInfoResult.vodPic) && Intrinsics.areEqual(this.vodYear, vodInfoResult.vodYear) && Intrinsics.areEqual(this.vodActor, vodInfoResult.vodActor) && Intrinsics.areEqual(this.vodDirector, vodInfoResult.vodDirector) && Intrinsics.areEqual(this.vodBlurb, vodInfoResult.vodBlurb) && Intrinsics.areEqual(this.typePid, vodInfoResult.typePid) && Intrinsics.areEqual(this.vodTotal, vodInfoResult.vodTotal) && Intrinsics.areEqual(this.vodSerial, vodInfoResult.vodSerial) && Intrinsics.areEqual(this.vodDoubanScore, vodInfoResult.vodDoubanScore) && Intrinsics.areEqual(this.vodTag, vodInfoResult.vodTag) && Intrinsics.areEqual(this.vodCollection, vodInfoResult.vodCollection) && Intrinsics.areEqual(this.audioTypeOption, vodInfoResult.audioTypeOption) && Intrinsics.areEqual(this.seriesInfo, vodInfoResult.seriesInfo) && Intrinsics.areEqual(this.audioLanguageTag, vodInfoResult.audioLanguageTag) && Intrinsics.areEqual(this.vodArea, vodInfoResult.vodArea);
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.vodName == null ? 0 : this.vodName.hashCode())) * 31) + (this.vodPic == null ? 0 : this.vodPic.hashCode())) * 31) + (this.vodYear == null ? 0 : this.vodYear.hashCode())) * 31) + (this.vodActor == null ? 0 : this.vodActor.hashCode())) * 31) + (this.vodDirector == null ? 0 : this.vodDirector.hashCode())) * 31) + (this.vodBlurb == null ? 0 : this.vodBlurb.hashCode())) * 31) + (this.typePid == null ? 0 : this.typePid.hashCode())) * 31) + (this.vodTotal == null ? 0 : this.vodTotal.hashCode())) * 31) + (this.vodSerial == null ? 0 : this.vodSerial.hashCode())) * 31) + (this.vodDoubanScore == null ? 0 : this.vodDoubanScore.hashCode())) * 31) + (this.vodTag == null ? 0 : this.vodTag.hashCode())) * 31) + (this.vodCollection == null ? 0 : this.vodCollection.hashCode())) * 31) + (this.audioTypeOption == null ? 0 : this.audioTypeOption.hashCode())) * 31) + (this.seriesInfo == null ? 0 : this.seriesInfo.hashCode())) * 31) + (this.audioLanguageTag == null ? 0 : this.audioLanguageTag.hashCode())) * 31) + (this.vodArea != null ? this.vodArea.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("VodInfoResult(id=").append(this.id).append(", vodName=").append(this.vodName).append(", vodPic=").append(this.vodPic).append(", vodYear=").append(this.vodYear).append(", vodActor=").append(this.vodActor).append(", vodDirector=").append(this.vodDirector).append(", vodBlurb=").append(this.vodBlurb).append(", typePid=").append(this.typePid).append(", vodTotal=").append(this.vodTotal).append(", vodSerial=").append(this.vodSerial).append(", vodDoubanScore=").append(this.vodDoubanScore).append(", vodTag=");
            sb.append(this.vodTag).append(", vodCollection=").append(this.vodCollection).append(", audioTypeOption=").append(this.audioTypeOption).append(", seriesInfo=").append(this.seriesInfo).append(", audioLanguageTag=").append(this.audioLanguageTag).append(", vodArea=").append(this.vodArea).append(')');
            return sb.toString();
        }

        public VodInfoResult(@JsonProperty("id") @Nullable Integer id, @JsonProperty("vod_name") @Nullable String vodName, @JsonProperty("vod_pic") @Nullable String vodPic, @JsonProperty("vod_year") @Nullable String vodYear, @JsonProperty("vod_actor") @Nullable String vodActor, @JsonProperty("vod_director") @Nullable String vodDirector, @JsonProperty("vod_blurb") @Nullable String vodBlurb, @JsonProperty("type_pid") @Nullable Integer typePid, @JsonProperty("vod_total") @Nullable Integer vodTotal, @JsonProperty("vod_serial") @Nullable Integer vodSerial, @JsonProperty("vod_douban_score") @Nullable Double vodDoubanScore, @JsonProperty("vod_tag") @Nullable String vodTag, @JsonProperty("vod_collection") @Nullable List<VodCollection> list, @JsonProperty("audio_type_option") @Nullable List<AudioTypeOption> list2, @JsonProperty("series_info") @Nullable List<SeriesInfo> list3, @JsonProperty("audio_language_tag") @Nullable String audioLanguageTag, @JsonProperty("vod_area") @Nullable String vodArea) {
            this.id = id;
            this.vodName = vodName;
            this.vodPic = vodPic;
            this.vodYear = vodYear;
            this.vodActor = vodActor;
            this.vodDirector = vodDirector;
            this.vodBlurb = vodBlurb;
            this.typePid = typePid;
            this.vodTotal = vodTotal;
            this.vodSerial = vodSerial;
            this.vodDoubanScore = vodDoubanScore;
            this.vodTag = vodTag;
            this.vodCollection = list;
            this.audioTypeOption = list2;
            this.seriesInfo = list3;
            this.audioLanguageTag = audioLanguageTag;
            this.vodArea = vodArea;
        }

        @Nullable
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        public final String getVodName() {
            return this.vodName;
        }

        @Nullable
        public final String getVodPic() {
            return this.vodPic;
        }

        @Nullable
        public final String getVodYear() {
            return this.vodYear;
        }

        @Nullable
        public final String getVodActor() {
            return this.vodActor;
        }

        @Nullable
        public final String getVodDirector() {
            return this.vodDirector;
        }

        @Nullable
        public final String getVodBlurb() {
            return this.vodBlurb;
        }

        @Nullable
        public final Integer getTypePid() {
            return this.typePid;
        }

        @Nullable
        public final Integer getVodTotal() {
            return this.vodTotal;
        }

        @Nullable
        public final Integer getVodSerial() {
            return this.vodSerial;
        }

        @Nullable
        public final Double getVodDoubanScore() {
            return this.vodDoubanScore;
        }

        @Nullable
        public final String getVodTag() {
            return this.vodTag;
        }

        @Nullable
        public final List<VodCollection> getVodCollection() {
            return this.vodCollection;
        }

        @Nullable
        public final List<AudioTypeOption> getAudioTypeOption() {
            return this.audioTypeOption;
        }

        @Nullable
        public final List<SeriesInfo> getSeriesInfo() {
            return this.seriesInfo;
        }

        @Nullable
        public final String getAudioLanguageTag() {
            return this.audioLanguageTag;
        }

        @Nullable
        public final String getVodArea() {
            return this.vodArea;
        }
    }

    /* JADX INFO: compiled from: CineTvProvider.kt */
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/CineTvProvider$VodInfoResponse;", "", "code", "", "message", "", "result", "Lcom/cncverse/CineTvProvider$VodInfoResult;", "<init>", "(ILjava/lang/String;Lcom/cncverse/CineTvProvider$VodInfoResult;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "getResult", "()Lcom/cncverse/CineTvProvider$VodInfoResult;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "CineTvProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VodInfoResponse {

        @JsonProperty("code")
        private final int code;

        @JsonProperty("message")
        @Nullable
        private final String message;

        @JsonProperty("result")
        @Nullable
        private final VodInfoResult result;

        public static /* synthetic */ VodInfoResponse copy$default(VodInfoResponse vodInfoResponse, int i, String str, VodInfoResult vodInfoResult, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = vodInfoResponse.code;
            }
            if ((i2 & 2) != 0) {
                str = vodInfoResponse.message;
            }
            if ((i2 & 4) != 0) {
                vodInfoResult = vodInfoResponse.result;
            }
            return vodInfoResponse.copy(i, str, vodInfoResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final VodInfoResult getResult() {
            return this.result;
        }

        @NotNull
        public final VodInfoResponse copy(@JsonProperty("code") int code, @JsonProperty("message") @Nullable String message, @JsonProperty("result") @Nullable VodInfoResult result) {
            return new VodInfoResponse(code, message, result);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VodInfoResponse)) {
                return false;
            }
            VodInfoResponse vodInfoResponse = (VodInfoResponse) other;
            return this.code == vodInfoResponse.code && Intrinsics.areEqual(this.message, vodInfoResponse.message) && Intrinsics.areEqual(this.result, vodInfoResponse.result);
        }

        public int hashCode() {
            return (((this.code * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.result != null ? this.result.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "VodInfoResponse(code=" + this.code + ", message=" + this.message + ", result=" + this.result + ')';
        }

        public VodInfoResponse(@JsonProperty("code") int code, @JsonProperty("message") @Nullable String message, @JsonProperty("result") @Nullable VodInfoResult result) {
            this.code = code;
            this.message = message;
            this.result = result;
        }

        public final int getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final VodInfoResult getResult() {
            return this.result;
        }
    }

    private final String des3Decrypt(String encryptedText) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            byte[] bytes = DES_KEY.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] bArrCopyOf = Arrays.copyOf(bytes, 24);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
            SecretKeySpec keySpec = new SecretKeySpec(bArrCopyOf, "DESede");
            byte[] bytes2 = DES_IV.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            IvParameterSpec ivSpec = new IvParameterSpec(bytes2);
            cipher.init(2, keySpec, ivSpec);
            byte[] encryptedData = Base64.decode(encryptedText, 0);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            return new String(decryptedData, Charsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("DES3 decryption failed: " + e.getMessage());
        }
    }

    private final String md5Hash(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = text.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] digest = md.digest(bytes);
        return ArraysKt.joinToString$default(digest, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.cncverse.CineTvProvider$$ExternalSyntheticLambda0
            public final Object invoke(Object obj) {
                return CineTvProvider.md5Hash$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence md5Hash$lambda$0(byte it) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    private final String generateSign(String curTime) throws Exception {
        String decryptedSecret = des3Decrypt(SECRET_KEY_ENCRYPTED);
        String signString = decryptedSecret + this.deviceId + curTime;
        String upperCase = md5Hash(signString).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    private final String generateP2pToken(String deviceId, String vodId, String timestamp) {
        String concatenated = "Zox882LYjEn4Rqpa" + deviceId + vodId + timestamp;
        String upperCase = md5Hash(concatenated).toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    private final String signVideoUrl(String url) throws NoSuchAlgorithmException {
        URI uri = new URI(url);
        String path = uri.getPath();
        String wsTime = Long.toHexString((System.currentTimeMillis() / ((long) 1000)) + ((long) 60));
        String raw = WS_SECRET + path + wsTime;
        String wsSecret = md5Hash(raw);
        String separator = StringsKt.contains$default(url, "?", false, 2, (Object) null) ? "&" : "?";
        return url + separator + "wsSecret=" + wsSecret + "&wsTime=" + wsTime;
    }

    @NotNull
    public Interceptor getVideoInterceptor(@NotNull ExtractorLink extractorLink) {
        return new Interceptor() { // from class: com.cncverse.CineTvProvider$$ExternalSyntheticLambda13
            public final Response intercept(Interceptor.Chain chain) {
                return CineTvProvider.getVideoInterceptor$lambda$0(this.f$0, chain);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Response getVideoInterceptor$lambda$0(CineTvProvider this$0, Interceptor.Chain chain) throws NoSuchAlgorithmException {
        Request request = chain.request();
        String newUrl = this$0.signVideoUrl(request.url().toString());
        Request newRequest = request.newBuilder().url(newUrl).build();
        return chain.proceed(newRequest);
    }

    private final String aesDecrypt(String encryptedBase64) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bytes = AES_KEY.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            SecretKeySpec keySpec = new SecretKeySpec(bytes, "AES");
            byte[] bytes2 = AES_IV.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            IvParameterSpec ivSpec = new IvParameterSpec(bytes2);
            cipher.init(2, keySpec, ivSpec);
            byte[] encryptedData = Base64.decode(encryptedBase64, 0);
            byte[] decryptedData = cipher.doFinal(encryptedData);
            if (decryptedData.length >= 2 && decryptedData[0] == 31 && decryptedData[1] == -117) {
                GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(decryptedData));
                Reader inputStreamReader = new InputStreamReader(gzipInputStream, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    BufferedReader it = bufferedReader;
                    String text = TextStreamsKt.readText(it);
                    CloseableKt.closeFinally(bufferedReader, (Throwable) null);
                    return text;
                } finally {
                }
            } else {
                return new String(decryptedData, Charsets.UTF_8);
            }
        } catch (Exception e) {
            throw new Exception("AES decryption failed: " + e.getMessage());
        }
    }

    static /* synthetic */ Object fetchDeviceToken$default(CineTvProvider cineTvProvider, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        return cineTvProvider.fetchDeviceToken(str, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    public final Object fetchDeviceToken(String invitedBy, Continuation<? super String> continuation) throws Exception {
        String responseText;
        UserInfo userInfo;
        String token;
        String strString;
        String url = getMainUrl() + "/api/public/init";
        String curTime = String.valueOf(System.currentTimeMillis());
        Map headers = MapsKt.mapOf(new Pair[]{TuplesKt.to("androidid", this.deviceId), TuplesKt.to("app_id", "filmin"), TuplesKt.to("app_language", "en"), TuplesKt.to("channel_code", "filmin_sh_1000"), TuplesKt.to("Connection", "Keep-Alive"), TuplesKt.to("Content-Type", "application/x-www-form-urlencoded"), TuplesKt.to("cur_time", curTime), TuplesKt.to("device_id", this.deviceId), TuplesKt.to("en_al", "0"), TuplesKt.to("gaid", this.gaid), TuplesKt.to("Host", "filmin.ajfysu.com"), TuplesKt.to("is_display", "GMT+05:30"), TuplesKt.to("is_language", "en"), TuplesKt.to("is_vvv", "0"), TuplesKt.to("log-header", "I am the log request header."), TuplesKt.to("mob_mfr", this.mobMfr), TuplesKt.to("mobmodel", this.mobModel), TuplesKt.to("package_name", "com.dramarush.shortin"), TuplesKt.to("sign", generateSign(curTime)), TuplesKt.to("sys_platform", "2"), TuplesKt.to("sysrelease", "13"), TuplesKt.to("token", ""), TuplesKt.to("User-Agent", "okhttp/4.11.0"), TuplesKt.to("version", "30000")});
        Request.Builder $this$fetchDeviceToken_u24lambda_u240 = new Request.Builder().url(url).post(new FormBody.Builder((Charset) null, 1, (DefaultConstructorMarker) null).add("invited_by", invitedBy).add("is_install", "1").build());
        for (Map.Entry element$iv : headers.entrySet()) {
            String key = (String) element$iv.getKey();
            String value = (String) element$iv.getValue();
            $this$fetchDeviceToken_u24lambda_u240.addHeader(key, value);
        }
        Request request = $this$fetchDeviceToken_u24lambda_u240.build();
        try {
            Response response = MainActivityKt.getApp().getBaseClient().newCall(request).execute();
            if (!response.isSuccessful()) {
                return "";
            }
            ResponseBody responseBodyBody = response.body();
            if (responseBodyBody == null || (strString = responseBodyBody.string()) == null || (responseText = StringsKt.trim(strString).toString()) == null) {
                responseText = "";
            }
            String jsonText = (!(responseText.length() > 0) || StringsKt.startsWith$default(responseText, "{", false, 2, (Object) null)) ? responseText : aesDecrypt(responseText);
            ObjectMapper $this$readValue$iv = this.mapper;
            String content$iv = jsonText;
            Object $this$checkTypeMismatch$iv$iv = $this$readValue$iv.readValue(content$iv, new TypeReference<InitResponse>() { // from class: com.cncverse.CineTvProvider$fetchDeviceToken$$inlined$readValue$1
            });
            if (!($this$checkTypeMismatch$iv$iv instanceof InitResponse)) {
                throw new RuntimeJsonMappingException("Deserialized value did not match the specified type; specified " + Reflection.getOrCreateKotlinClass(InitResponse.class).getQualifiedName() + "(non-null) but was " + ($this$checkTypeMismatch$iv$iv != null ? Reflection.getOrCreateKotlinClass($this$checkTypeMismatch$iv$iv.getClass()).getQualifiedName() : null));
            }
            InitResponse initResponse = (InitResponse) $this$checkTypeMismatch$iv$iv;
            InitResult result = initResponse.getResult();
            return (result == null || (userInfo = result.getUserInfo()) == null || (token = userInfo.getToken()) == null) ? "" : token;
        } catch (Exception e) {
            throw new Exception("Failed to fetch device token: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:29:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getHeaders(java.lang.String r10, kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, java.lang.String>> r11) {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.getHeaders(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object getHeaders$default(CineTvProvider cineTvProvider, String str, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return cineTvProvider.getHeaders(str, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchRecommend(int r22, kotlin.coroutines.Continuation<? super com.cncverse.CineTvProvider.ApiResponse> r23) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.searchRecommend(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object searchRecommend$default(CineTvProvider cineTvProvider, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 1;
        }
        return cineTvProvider.searchRecommend(i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object topicVodList(int r22, int r23, kotlin.coroutines.Continuation<? super java.util.List<com.cncverse.CineTvProvider.VodItem>> r24) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.topicVodList(int, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object topicVodList$default(CineTvProvider cineTvProvider, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 1;
        }
        return cineTvProvider.topicVodList(i, i2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchVod(java.lang.String r22, int r23, kotlin.coroutines.Continuation<? super com.cncverse.CineTvProvider.ApiResponse> r24) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.searchVod(java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object searchVod$default(CineTvProvider cineTvProvider, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return cineTvProvider.searchVod(str, i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getVodInfo(java.lang.String r23, int r24, kotlin.coroutines.Continuation<? super com.cncverse.CineTvProvider.VodInfoResponse> r25) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 442
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.getVodInfo(java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object getVodInfo$default(CineTvProvider cineTvProvider, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return cineTvProvider.getVodInfo(str, i, continuation);
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r26, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r27, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r28) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$0(VodItem $vod, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($vod.getVodPic());
        String vodYear = $vod.getVodYear();
        $this$newMovieSearchResponse.setYear(vodYear != null ? StringsKt.toIntOrNull(vodYear) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$1(VodItem $vod, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl($vod.getVodPic());
        String vodYear = $vod.getVodYear();
        $this$newTvSeriesSearchResponse.setYear(vodYear != null ? StringsKt.toIntOrNull(vodYear) : null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r24) {
        /*
            Method dump skipped, instruction units count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$0(VodItem $vod, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($vod.getVodPic());
        String vodYear = $vod.getVodYear();
        $this$newMovieSearchResponse.setYear(vodYear != null ? StringsKt.toIntOrNull(vodYear) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$1(VodItem $vod, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl($vod.getVodPic());
        String vodYear = $vod.getVodYear();
        $this$newTvSeriesSearchResponse.setYear(vodYear != null ? StringsKt.toIntOrNull(vodYear) : null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0466 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0467  */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r40, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r41) {
        /*
            Method dump skipped, instruction units count: 1192
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider$load$2", f = "CineTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $plot;
        final /* synthetic */ String $posterUrl;
        final /* synthetic */ Score $score;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Integer num, String str2, Score score, List<String> list, List<ActorData> list2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$posterUrl = str;
            this.$year = num;
            this.$plot = str2;
            this.$score = score;
            this.$tags = list;
            this.$actors = list2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$posterUrl, this.$year, this.$plot, this.$score, this.$tags, this.$actors, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return create(movieLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            MovieLoadResponse $this$newMovieLoadResponse = (MovieLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newMovieLoadResponse.setPosterUrl(this.$posterUrl);
                    $this$newMovieLoadResponse.setYear(this.$year);
                    $this$newMovieLoadResponse.setPlot(this.$plot);
                    $this$newMovieLoadResponse.setScore(this.$score);
                    $this$newMovieLoadResponse.setTags(this.$tags);
                    $this$newMovieLoadResponse.setActors(this.$actors);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$4$0(VodCollection $collection, Episode $this$newEpisode) {
        $this$newEpisode.setName("Episode " + $collection.getTitle());
        $this$newEpisode.setSeason(1);
        $this$newEpisode.setEpisode($collection.getCollection());
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$load$3, reason: invalid class name */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider$load$3", f = "CineTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $plot;
        final /* synthetic */ String $posterUrl;
        final /* synthetic */ Score $score;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, Integer num, String str2, Score score, List<String> list, List<ActorData> list2, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$posterUrl = str;
            this.$year = num;
            this.$plot = str2;
            this.$score = score;
            this.$tags = list;
            this.$actors = list2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$posterUrl, this.$year, this.$plot, this.$score, this.$tags, this.$actors, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return create(tvSeriesLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            TvSeriesLoadResponse $this$newTvSeriesLoadResponse = (TvSeriesLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newTvSeriesLoadResponse.setPosterUrl(this.$posterUrl);
                    $this$newTvSeriesLoadResponse.setYear(this.$year);
                    $this$newTvSeriesLoadResponse.setPlot(this.$plot);
                    $this$newTvSeriesLoadResponse.setScore(this.$score);
                    $this$newTvSeriesLoadResponse.setTags(this.$tags);
                    $this$newTvSeriesLoadResponse.setActors(this.$actors);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x027d  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r28, boolean r29, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r30, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r32) throws java.security.NoSuchAlgorithmException {
        /*
            Method dump skipped, instruction units count: 666
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.CineTvProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.cncverse.CineTvProvider$loadLinks$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CineTvProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.CineTvProvider$loadLinks$3", f = "CineTvProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00043 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00043(Continuation<? super C00043> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00043 = CineTvProvider.this.new C00043(continuation);
            c00043.L$0 = obj;
            return c00043;
        }

        public final Object invoke(ExtractorLink extractorLink, Continuation<? super Unit> continuation) {
            return create(extractorLink, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            ExtractorLink $this$newExtractorLink = (ExtractorLink) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    $this$newExtractorLink.setReferer(CineTvProvider.this.getMainUrl());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
