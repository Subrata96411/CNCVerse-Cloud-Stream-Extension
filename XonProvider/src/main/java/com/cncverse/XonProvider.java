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
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.ExtensionsKt;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.ExtractorLinkType;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: XonProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nXonProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 XonProvider.kt\ncom/cncverse/XonProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 6 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,780:1\n1#2:781\n1#2:793\n1#2:806\n116#3:782\n54#3:783\n117#3:784\n61#3,8:785\n71#3:794\n116#3:795\n54#3:796\n117#3:797\n61#3,8:798\n71#3:807\n777#4:808\n873#4,2:809\n1586#4:811\n1661#4,3:812\n777#4:815\n873#4,2:816\n1586#4:818\n1661#4,3:819\n1586#4:822\n1661#4,3:823\n1512#4:826\n1538#4,3:827\n1541#4,3:837\n1586#4:841\n1661#4,3:842\n296#4:846\n1807#4,3:847\n297#4:850\n832#4:851\n862#4,2:852\n777#4:854\n873#4,2:855\n1915#4,2:857\n777#4:859\n873#4,2:860\n1915#4,2:862\n777#4:864\n873#4,2:865\n1586#4:867\n1661#4,3:868\n1205#4,2:871\n1282#4,4:873\n1586#4:877\n1661#4,3:878\n383#5,7:830\n221#6:840\n222#6:845\n*S KotlinDebug\n*F\n+ 1 XonProvider.kt\ncom/cncverse/XonProvider\n*L\n200#1:793\n205#1:806\n200#1:782\n200#1:783\n200#1:784\n200#1:785,8\n200#1:794\n205#1:795\n205#1:796\n205#1:797\n205#1:798,8\n205#1:807\n257#1:808\n257#1:809,2\n259#1:811\n259#1:812,3\n269#1:815\n269#1:816,2\n271#1:818\n271#1:819,3\n281#1:822\n281#1:823,3\n291#1:826\n291#1:827,3\n291#1:837,3\n293#1:841\n293#1:842,3\n325#1:846\n326#1:847,3\n325#1:850\n329#1:851\n329#1:852,2\n341#1:854\n341#1:855,2\n354#1:857,2\n363#1:859\n363#1:860,2\n373#1:862,2\n421#1:864\n421#1:865,2\n426#1:867\n426#1:868,3\n427#1:871,2\n427#1:873,4\n429#1:877\n429#1:878,3\n291#1:830,7\n292#1:840\n292#1:845\n*E\n"})
public final class XonProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;
    private long configExpireTime;
    private boolean configFetched;
    private long lastCacheTime;

    @NotNull
    private String mainUrl = "https://xon-avens.xyz/apis";

    @NotNull
    private String name = "Xon";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries, TvType.Anime});

    @NotNull
    private String apiKey = "553y845hfhdlfhjkl438943943839443943fdhdkfjfj9834lnfd98";

    @NotNull
    private String callerName = "vion-official-app";
    private final ObjectMapper mapper = ExtensionsKt.jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @NotNull
    private List<Movie> cachedMovies = CollectionsKt.emptyList();

    @NotNull
    private List<Episode> cachedEpisodes = CollectionsKt.emptyList();
    private final long cacheRefreshInterval = 86400000;

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("trending", "Trending"), TuplesKt.to("latest_episodes", "Latest Episodes"), TuplesKt.to("movies", "Movies")});

    @NotNull
    private final List<String> knownLanguages = CollectionsKt.listOf(new String[]{"tamil", "hindi", "telugu", "malayalam", "kannada", "english", "bengali", "marathi", "punjabi", "gujarati", "odia", "urdu", "assamese", "japanese", "korean", "chinese"});

    /* JADX INFO: renamed from: com.cncverse.XonProvider$addVideoLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3}, l = {525, 526, 527, 528}, m = "addVideoLinks", n = {"basic", "sd", "hd", "fhd", "callback", "basic", "sd", "hd", "fhd", "callback", "basic", "sd", "hd", "fhd", "callback", "basic", "sd", "hd", "fhd", "callback"}, nl = {526, 527, 528, 529}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.addVideoLinks(null, null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$fetchRemoteConfig$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {}, l = {168}, m = "fetchRemoteConfig", n = {}, nl = {169}, s = {}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.fetchRemoteConfig((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {0, 0}, l = {250}, m = "getMainPage", n = {"request", "page"}, nl = {251}, s = {"L$0", "I$0"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {387, 399, 443}, m = "load", n = {"url", "url", "str", "parts", "type", "movie", "id", "url", "str", "parts", "type", "ep", "showEpisodes", "seasonIds", "seasonNoMap", "episodeList", "showTitle", "langLabel", "displayTitle", "id"}, nl = {388, 417, 459}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "I$0"}, v = 2)
    static final class C00051 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
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

        C00051(Continuation<? super C00051> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6}, l = {487, 498, 499, 500, 505, 506, 507}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "isCasting", "data", "subtitleCallback", "callback", "str", "parts", "type", "ep", "isCasting", "id", "data", "subtitleCallback", "callback", "str", "parts", "type", "ep", "isCasting", "id", "data", "subtitleCallback", "callback", "str", "parts", "type", "ep", "isCasting", "id", "data", "subtitleCallback", "callback", "str", "parts", "type", "movie", "isCasting", "id", "data", "subtitleCallback", "callback", "str", "parts", "type", "movie", "isCasting", "id", "data", "subtitleCallback", "callback", "str", "parts", "type", "movie", "isCasting", "id"}, nl = {488, 499, 500, 503, 506, 507, 510}, s = {"L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0"}, v = 2)
    static final class C00061 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00061(Continuation<? super C00061> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$refreshCache$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {0, 1, 1, 2, 2, 2, 2}, l = {185, 199, 204}, m = "refreshCache", n = {"currentTime", "headers", "currentTime", "headers", "moviesRaw", "moviesResponse", "currentTime"}, nl = {188, 200, 205}, s = {"J$0", "L$0", "J$0", "L$0", "L$1", "L$2", "J$0"}, v = 2)
    static final class C00081 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00081(Continuation<? super C00081> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.refreshCache((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider", f = "XonProvider.kt", i = {0}, l = {335}, m = "search", n = {"query"}, nl = {336}, s = {"L$0"}, v = 2)
    static final class C00091 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00091(Continuation<? super C00091> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return XonProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/XonProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "XonProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return XonProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            XonProvider.context = context;
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

    private final Map<String, String> getHeaders() {
        String host = "xon-avens.xyz";
        try {
            String host2 = new URI(getMainUrl()).getHost();
            if (host2 != null) {
                host = host2;
            }
        } catch (Exception e) {
        }
        return MapsKt.mapOf(new Pair[]{TuplesKt.to("Accept-Encoding", "gzip"), TuplesKt.to("API", this.apiKey), TuplesKt.to("CALLER", this.callerName), TuplesKt.to("Connection", "Keep-Alive"), TuplesKt.to("Host", host), TuplesKt.to("User-Agent", "okhttp/5.3.2")});
    }

    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\bl\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008f\u0003\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0014\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u001a\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u001c\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u001f\u001a\u00020\u0003\u0012\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010#\u001a\u00020\u0003\u0012\n\b\u0003\u0010$\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b'\u0010(J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\u0006HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010X\u001a\u00020\u0003HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\u000b\u0010_\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010a\u001a\u00020\u0003HÆ\u0003J\t\u0010b\u001a\u00020\u0003HÆ\u0003J\t\u0010c\u001a\u00020\u0003HÆ\u0003J\t\u0010d\u001a\u00020\u0003HÆ\u0003J\t\u0010e\u001a\u00020\u0003HÆ\u0003J\t\u0010f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010i\u001a\u00020\u0003HÆ\u0003J\u000b\u0010j\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010m\u001a\u00020\u0003HÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010o\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0091\u0003\u0010q\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u000e\u001a\u00020\u00032\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0013\u001a\u00020\u00032\b\b\u0003\u0010\u0014\u001a\u00020\u00032\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0017\u001a\u00020\u00032\b\b\u0003\u0010\u0018\u001a\u00020\u00032\b\b\u0003\u0010\u0019\u001a\u00020\u00032\b\b\u0003\u0010\u001a\u001a\u00020\u00032\b\b\u0003\u0010\u001b\u001a\u00020\u00032\b\b\u0003\u0010\u001c\u001a\u00020\u00032\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u001f\u001a\u00020\u00032\n\b\u0003\u0010 \u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010!\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\"\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010#\u001a\u00020\u00032\n\b\u0003\u0010$\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010%\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010r\u001a\u00020s2\b\u0010t\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010u\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010v\u001a\u00020\u0006HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010*R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010-R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010-R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010-R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010-R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010-R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010-R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010*R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010-R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010-R\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010-R\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010-R\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010*R\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010*R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010-R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010-R\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010*R\u0016\u0010\u0018\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010*R\u0016\u0010\u0019\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010*R\u0016\u0010\u001a\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010*R\u0016\u0010\u001b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010*R\u0016\u0010\u001c\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010*R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010-R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010-R\u0016\u0010\u001f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010*R\u0018\u0010 \u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010-R\u0018\u0010!\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010-R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010-R\u0016\u0010#\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010*R\u0018\u0010$\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010-R\u0018\u0010%\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010-R\u0018\u0010&\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010-¨\u0006w"}, d2 = {"Lcom/cncverse/XonProvider$Movie;", "", "id", "", "no", "name", "", "poster", "cover", "genre", "des", "tags", "type", "trailer", "ttype", "basic", "sd", "hd", "fhd", "showId", "language", "showName", "languageName", "premium", "wfeathers", "bfeathers", "sfeathers", "trending", "special", "xPlayer2", "xPlayer3", "locked", "rating", "avgRuntime", "ageRating", "top10", "playCode", "createdAt", "updatedAt", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;IIIIIILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getNo", "getName", "()Ljava/lang/String;", "getPoster", "getCover", "getGenre", "getDes", "getTags", "getType", "getTrailer", "getTtype", "getBasic", "getSd", "getHd", "getFhd", "getShowId", "getLanguage", "getShowName", "getLanguageName", "getPremium", "getWfeathers", "getBfeathers", "getSfeathers", "getTrending", "getSpecial", "getXPlayer2", "getXPlayer3", "getLocked", "getRating", "getAvgRuntime", "getAgeRating", "getTop10", "getPlayCode", "getCreatedAt", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "copy", "equals", "", "other", "hashCode", "toString", "XonProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Movie {

        @JsonProperty("age_rating")
        @Nullable
        private final String ageRating;

        @JsonProperty("avg_runtime")
        @Nullable
        private final String avgRuntime;

        @JsonProperty("basic")
        @Nullable
        private final String basic;

        @JsonProperty("bfeathers")
        private final int bfeathers;

        @JsonProperty("cover")
        @Nullable
        private final String cover;

        @JsonProperty("created_at")
        @Nullable
        private final String createdAt;

        @JsonProperty("des")
        @Nullable
        private final String des;

        @JsonProperty("fhd")
        @Nullable
        private final String fhd;

        @JsonProperty("genre")
        @Nullable
        private final String genre;

        @JsonProperty("hd")
        @Nullable
        private final String hd;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("language")
        private final int language;

        @JsonProperty("language_name")
        @Nullable
        private final String languageName;

        @JsonProperty("locked")
        private final int locked;

        @JsonProperty("name")
        @NotNull
        private final String name;

        @JsonProperty("no")
        private final int no;

        @JsonProperty("play_code")
        @Nullable
        private final String playCode;

        @JsonProperty("poster")
        @Nullable
        private final String poster;

        @JsonProperty("premium")
        private final int premium;

        @JsonProperty("rating")
        @Nullable
        private final String rating;

        @JsonProperty("sd")
        @Nullable
        private final String sd;

        @JsonProperty("sfeathers")
        private final int sfeathers;

        @JsonProperty("show_id")
        private final int showId;

        @JsonProperty("show_name")
        @Nullable
        private final String showName;

        @JsonProperty("special")
        private final int special;

        @JsonProperty("tags")
        @Nullable
        private final String tags;

        @JsonProperty("top10")
        private final int top10;

        @JsonProperty("trailer")
        @Nullable
        private final String trailer;

        @JsonProperty("trending")
        private final int trending;

        @JsonProperty("ttype")
        private final int ttype;

        @JsonProperty("type")
        @Nullable
        private final String type;

        @JsonProperty("updated_at")
        @Nullable
        private final String updatedAt;

        @JsonProperty("wfeathers")
        private final int wfeathers;

        @JsonProperty("xPlayer2")
        @Nullable
        private final String xPlayer2;

        @JsonProperty("xPlayer3")
        @Nullable
        private final String xPlayer3;

        public static /* synthetic */ Movie copy$default(Movie movie, int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i3, String str9, String str10, String str11, String str12, int i4, int i5, String str13, String str14, int i6, int i7, int i8, int i9, int i10, int i11, String str15, String str16, int i12, String str17, String str18, String str19, int i13, String str20, String str21, String str22, int i14, int i15, Object obj) {
            String str23;
            String str24;
            String str25;
            int i16;
            int i17;
            int i18;
            int i19;
            int i20;
            int i21;
            String str26;
            String str27;
            int i22;
            String str28;
            String str29;
            String str30;
            int i23;
            String str31;
            String str32;
            String str33;
            String str34;
            String str35;
            String str36;
            String str37;
            String str38;
            String str39;
            int i24;
            String str40;
            String str41;
            String str42;
            int i25;
            int i26;
            String str43;
            int i27;
            String str44;
            int i28 = (i14 & 1) != 0 ? movie.id : i;
            int i29 = (i14 & 2) != 0 ? movie.no : i2;
            String str45 = (i14 & 4) != 0 ? movie.name : str;
            String str46 = (i14 & 8) != 0 ? movie.poster : str2;
            String str47 = (i14 & 16) != 0 ? movie.cover : str3;
            String str48 = (i14 & 32) != 0 ? movie.genre : str4;
            String str49 = (i14 & 64) != 0 ? movie.des : str5;
            String str50 = (i14 & 128) != 0 ? movie.tags : str6;
            String str51 = (i14 & 256) != 0 ? movie.type : str7;
            String str52 = (i14 & 512) != 0 ? movie.trailer : str8;
            int i30 = (i14 & 1024) != 0 ? movie.ttype : i3;
            String str53 = (i14 & 2048) != 0 ? movie.basic : str9;
            String str54 = (i14 & 4096) != 0 ? movie.sd : str10;
            String str55 = (i14 & 8192) != 0 ? movie.hd : str11;
            int i31 = i28;
            String str56 = (i14 & 16384) != 0 ? movie.fhd : str12;
            int i32 = (i14 & 32768) != 0 ? movie.showId : i4;
            int i33 = (i14 & 65536) != 0 ? movie.language : i5;
            String str57 = (i14 & 131072) != 0 ? movie.showName : str13;
            String str58 = (i14 & 262144) != 0 ? movie.languageName : str14;
            int i34 = (i14 & 524288) != 0 ? movie.premium : i6;
            int i35 = (i14 & 1048576) != 0 ? movie.wfeathers : i7;
            int i36 = (i14 & 2097152) != 0 ? movie.bfeathers : i8;
            int i37 = (i14 & 4194304) != 0 ? movie.sfeathers : i9;
            int i38 = (i14 & 8388608) != 0 ? movie.trending : i10;
            int i39 = (i14 & 16777216) != 0 ? movie.special : i11;
            String str59 = (i14 & 33554432) != 0 ? movie.xPlayer2 : str15;
            String str60 = (i14 & 67108864) != 0 ? movie.xPlayer3 : str16;
            int i40 = (i14 & 134217728) != 0 ? movie.locked : i12;
            String str61 = (i14 & 268435456) != 0 ? movie.rating : str17;
            String str62 = (i14 & 536870912) != 0 ? movie.avgRuntime : str18;
            String str63 = (i14 & 1073741824) != 0 ? movie.ageRating : str19;
            int i41 = (i14 & Integer.MIN_VALUE) != 0 ? movie.top10 : i13;
            String str64 = (i15 & 1) != 0 ? movie.playCode : str20;
            String str65 = (i15 & 2) != 0 ? movie.createdAt : str21;
            if ((i15 & 4) != 0) {
                str24 = str65;
                str23 = movie.updatedAt;
                i16 = i34;
                i17 = i35;
                i18 = i36;
                i19 = i37;
                i20 = i38;
                i21 = i39;
                str26 = str59;
                str27 = str60;
                i22 = i40;
                str28 = str61;
                str29 = str62;
                str30 = str63;
                i23 = i41;
                str31 = str64;
                str32 = str56;
                str34 = str47;
                str35 = str48;
                str36 = str49;
                str37 = str50;
                str38 = str51;
                str39 = str52;
                i24 = i30;
                str40 = str53;
                str41 = str54;
                str42 = str55;
                i25 = i32;
                i26 = i33;
                str43 = str57;
                str25 = str58;
                i27 = i29;
                str44 = str45;
                str33 = str46;
            } else {
                str23 = str22;
                str24 = str65;
                str25 = str58;
                i16 = i34;
                i17 = i35;
                i18 = i36;
                i19 = i37;
                i20 = i38;
                i21 = i39;
                str26 = str59;
                str27 = str60;
                i22 = i40;
                str28 = str61;
                str29 = str62;
                str30 = str63;
                i23 = i41;
                str31 = str64;
                str32 = str56;
                str33 = str46;
                str34 = str47;
                str35 = str48;
                str36 = str49;
                str37 = str50;
                str38 = str51;
                str39 = str52;
                i24 = i30;
                str40 = str53;
                str41 = str54;
                str42 = str55;
                i25 = i32;
                i26 = i33;
                str43 = str57;
                i27 = i29;
                str44 = str45;
            }
            return movie.copy(i31, i27, str44, str33, str34, str35, str36, str37, str38, str39, i24, str40, str41, str42, str32, i25, i26, str43, str25, i16, i17, i18, i19, i20, i21, str26, str27, i22, str28, str29, str30, i23, str31, str24, str23);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getTrailer() {
            return this.trailer;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getTtype() {
            return this.ttype;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getBasic() {
            return this.basic;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getSd() {
            return this.sd;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getHd() {
            return this.hd;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getFhd() {
            return this.fhd;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final int getShowId() {
            return this.showId;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final int getLanguage() {
            return this.language;
        }

        @Nullable
        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getShowName() {
            return this.showName;
        }

        @Nullable
        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getLanguageName() {
            return this.languageName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getNo() {
            return this.no;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final int getPremium() {
            return this.premium;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final int getWfeathers() {
            return this.wfeathers;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final int getBfeathers() {
            return this.bfeathers;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final int getSfeathers() {
            return this.sfeathers;
        }

        /* JADX INFO: renamed from: component24, reason: from getter */
        public final int getTrending() {
            return this.trending;
        }

        /* JADX INFO: renamed from: component25, reason: from getter */
        public final int getSpecial() {
            return this.special;
        }

        @Nullable
        /* JADX INFO: renamed from: component26, reason: from getter */
        public final String getXPlayer2() {
            return this.xPlayer2;
        }

        @Nullable
        /* JADX INFO: renamed from: component27, reason: from getter */
        public final String getXPlayer3() {
            return this.xPlayer3;
        }

        /* JADX INFO: renamed from: component28, reason: from getter */
        public final int getLocked() {
            return this.locked;
        }

        @Nullable
        /* JADX INFO: renamed from: component29, reason: from getter */
        public final String getRating() {
            return this.rating;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component30, reason: from getter */
        public final String getAvgRuntime() {
            return this.avgRuntime;
        }

        @Nullable
        /* JADX INFO: renamed from: component31, reason: from getter */
        public final String getAgeRating() {
            return this.ageRating;
        }

        /* JADX INFO: renamed from: component32, reason: from getter */
        public final int getTop10() {
            return this.top10;
        }

        @Nullable
        /* JADX INFO: renamed from: component33, reason: from getter */
        public final String getPlayCode() {
            return this.playCode;
        }

        @Nullable
        /* JADX INFO: renamed from: component34, reason: from getter */
        public final String getCreatedAt() {
            return this.createdAt;
        }

        @Nullable
        /* JADX INFO: renamed from: component35, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getDes() {
            return this.des;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getTags() {
            return this.tags;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @NotNull
        public final Movie copy(@JsonProperty("id") int id, @JsonProperty("no") int no, @JsonProperty("name") @NotNull String name, @JsonProperty("poster") @Nullable String poster, @JsonProperty("cover") @Nullable String cover, @JsonProperty("genre") @Nullable String genre, @JsonProperty("des") @Nullable String des, @JsonProperty("tags") @Nullable String tags, @JsonProperty("type") @Nullable String type, @JsonProperty("trailer") @Nullable String trailer, @JsonProperty("ttype") int ttype, @JsonProperty("basic") @Nullable String basic, @JsonProperty("sd") @Nullable String sd, @JsonProperty("hd") @Nullable String hd, @JsonProperty("fhd") @Nullable String fhd, @JsonProperty("show_id") int showId, @JsonProperty("language") int language, @JsonProperty("show_name") @Nullable String showName, @JsonProperty("language_name") @Nullable String languageName, @JsonProperty("premium") int premium, @JsonProperty("wfeathers") int wfeathers, @JsonProperty("bfeathers") int bfeathers, @JsonProperty("sfeathers") int sfeathers, @JsonProperty("trending") int trending, @JsonProperty("special") int special, @JsonProperty("xPlayer2") @Nullable String xPlayer2, @JsonProperty("xPlayer3") @Nullable String xPlayer3, @JsonProperty("locked") int locked, @JsonProperty("rating") @Nullable String rating, @JsonProperty("avg_runtime") @Nullable String avgRuntime, @JsonProperty("age_rating") @Nullable String ageRating, @JsonProperty("top10") int top10, @JsonProperty("play_code") @Nullable String playCode, @JsonProperty("created_at") @Nullable String createdAt, @JsonProperty("updated_at") @Nullable String updatedAt) {
            return new Movie(id, no, name, poster, cover, genre, des, tags, type, trailer, ttype, basic, sd, hd, fhd, showId, language, showName, languageName, premium, wfeathers, bfeathers, sfeathers, trending, special, xPlayer2, xPlayer3, locked, rating, avgRuntime, ageRating, top10, playCode, createdAt, updatedAt);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Movie)) {
                return false;
            }
            Movie movie = (Movie) other;
            return this.id == movie.id && this.no == movie.no && Intrinsics.areEqual(this.name, movie.name) && Intrinsics.areEqual(this.poster, movie.poster) && Intrinsics.areEqual(this.cover, movie.cover) && Intrinsics.areEqual(this.genre, movie.genre) && Intrinsics.areEqual(this.des, movie.des) && Intrinsics.areEqual(this.tags, movie.tags) && Intrinsics.areEqual(this.type, movie.type) && Intrinsics.areEqual(this.trailer, movie.trailer) && this.ttype == movie.ttype && Intrinsics.areEqual(this.basic, movie.basic) && Intrinsics.areEqual(this.sd, movie.sd) && Intrinsics.areEqual(this.hd, movie.hd) && Intrinsics.areEqual(this.fhd, movie.fhd) && this.showId == movie.showId && this.language == movie.language && Intrinsics.areEqual(this.showName, movie.showName) && Intrinsics.areEqual(this.languageName, movie.languageName) && this.premium == movie.premium && this.wfeathers == movie.wfeathers && this.bfeathers == movie.bfeathers && this.sfeathers == movie.sfeathers && this.trending == movie.trending && this.special == movie.special && Intrinsics.areEqual(this.xPlayer2, movie.xPlayer2) && Intrinsics.areEqual(this.xPlayer3, movie.xPlayer3) && this.locked == movie.locked && Intrinsics.areEqual(this.rating, movie.rating) && Intrinsics.areEqual(this.avgRuntime, movie.avgRuntime) && Intrinsics.areEqual(this.ageRating, movie.ageRating) && this.top10 == movie.top10 && Intrinsics.areEqual(this.playCode, movie.playCode) && Intrinsics.areEqual(this.createdAt, movie.createdAt) && Intrinsics.areEqual(this.updatedAt, movie.updatedAt);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.id * 31) + this.no) * 31) + this.name.hashCode()) * 31) + (this.poster == null ? 0 : this.poster.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.genre == null ? 0 : this.genre.hashCode())) * 31) + (this.des == null ? 0 : this.des.hashCode())) * 31) + (this.tags == null ? 0 : this.tags.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.trailer == null ? 0 : this.trailer.hashCode())) * 31) + this.ttype) * 31) + (this.basic == null ? 0 : this.basic.hashCode())) * 31) + (this.sd == null ? 0 : this.sd.hashCode())) * 31) + (this.hd == null ? 0 : this.hd.hashCode())) * 31) + (this.fhd == null ? 0 : this.fhd.hashCode())) * 31) + this.showId) * 31) + this.language) * 31) + (this.showName == null ? 0 : this.showName.hashCode())) * 31) + (this.languageName == null ? 0 : this.languageName.hashCode())) * 31) + this.premium) * 31) + this.wfeathers) * 31) + this.bfeathers) * 31) + this.sfeathers) * 31) + this.trending) * 31) + this.special) * 31) + (this.xPlayer2 == null ? 0 : this.xPlayer2.hashCode())) * 31) + (this.xPlayer3 == null ? 0 : this.xPlayer3.hashCode())) * 31) + this.locked) * 31) + (this.rating == null ? 0 : this.rating.hashCode())) * 31) + (this.avgRuntime == null ? 0 : this.avgRuntime.hashCode())) * 31) + (this.ageRating == null ? 0 : this.ageRating.hashCode())) * 31) + this.top10) * 31) + (this.playCode == null ? 0 : this.playCode.hashCode())) * 31) + (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 31) + (this.updatedAt != null ? this.updatedAt.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Movie(id=").append(this.id).append(", no=").append(this.no).append(", name=").append(this.name).append(", poster=").append(this.poster).append(", cover=").append(this.cover).append(", genre=").append(this.genre).append(", des=").append(this.des).append(", tags=").append(this.tags).append(", type=").append(this.type).append(", trailer=").append(this.trailer).append(", ttype=").append(this.ttype).append(", basic=");
            sb.append(this.basic).append(", sd=").append(this.sd).append(", hd=").append(this.hd).append(", fhd=").append(this.fhd).append(", showId=").append(this.showId).append(", language=").append(this.language).append(", showName=").append(this.showName).append(", languageName=").append(this.languageName).append(", premium=").append(this.premium).append(", wfeathers=").append(this.wfeathers).append(", bfeathers=").append(this.bfeathers).append(", sfeathers=").append(this.sfeathers);
            sb.append(", trending=").append(this.trending).append(", special=").append(this.special).append(", xPlayer2=").append(this.xPlayer2).append(", xPlayer3=").append(this.xPlayer3).append(", locked=").append(this.locked).append(", rating=").append(this.rating).append(", avgRuntime=").append(this.avgRuntime).append(", ageRating=").append(this.ageRating).append(", top10=").append(this.top10).append(", playCode=").append(this.playCode).append(", createdAt=").append(this.createdAt).append(", updatedAt=");
            sb.append(this.updatedAt).append(')');
            return sb.toString();
        }

        public Movie(@JsonProperty("id") int id, @JsonProperty("no") int no, @JsonProperty("name") @NotNull String name, @JsonProperty("poster") @Nullable String poster, @JsonProperty("cover") @Nullable String cover, @JsonProperty("genre") @Nullable String genre, @JsonProperty("des") @Nullable String des, @JsonProperty("tags") @Nullable String tags, @JsonProperty("type") @Nullable String type, @JsonProperty("trailer") @Nullable String trailer, @JsonProperty("ttype") int ttype, @JsonProperty("basic") @Nullable String basic, @JsonProperty("sd") @Nullable String sd, @JsonProperty("hd") @Nullable String hd, @JsonProperty("fhd") @Nullable String fhd, @JsonProperty("show_id") int showId, @JsonProperty("language") int language, @JsonProperty("show_name") @Nullable String showName, @JsonProperty("language_name") @Nullable String languageName, @JsonProperty("premium") int premium, @JsonProperty("wfeathers") int wfeathers, @JsonProperty("bfeathers") int bfeathers, @JsonProperty("sfeathers") int sfeathers, @JsonProperty("trending") int trending, @JsonProperty("special") int special, @JsonProperty("xPlayer2") @Nullable String xPlayer2, @JsonProperty("xPlayer3") @Nullable String xPlayer3, @JsonProperty("locked") int locked, @JsonProperty("rating") @Nullable String rating, @JsonProperty("avg_runtime") @Nullable String avgRuntime, @JsonProperty("age_rating") @Nullable String ageRating, @JsonProperty("top10") int top10, @JsonProperty("play_code") @Nullable String playCode, @JsonProperty("created_at") @Nullable String createdAt, @JsonProperty("updated_at") @Nullable String updatedAt) {
            this.id = id;
            this.no = no;
            this.name = name;
            this.poster = poster;
            this.cover = cover;
            this.genre = genre;
            this.des = des;
            this.tags = tags;
            this.type = type;
            this.trailer = trailer;
            this.ttype = ttype;
            this.basic = basic;
            this.sd = sd;
            this.hd = hd;
            this.fhd = fhd;
            this.showId = showId;
            this.language = language;
            this.showName = showName;
            this.languageName = languageName;
            this.premium = premium;
            this.wfeathers = wfeathers;
            this.bfeathers = bfeathers;
            this.sfeathers = sfeathers;
            this.trending = trending;
            this.special = special;
            this.xPlayer2 = xPlayer2;
            this.xPlayer3 = xPlayer3;
            this.locked = locked;
            this.rating = rating;
            this.avgRuntime = avgRuntime;
            this.ageRating = ageRating;
            this.top10 = top10;
            this.playCode = playCode;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public /* synthetic */ Movie(int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i3, String str9, String str10, String str11, String str12, int i4, int i5, String str13, String str14, int i6, int i7, int i8, int i9, int i10, int i11, String str15, String str16, int i12, String str17, String str18, String str19, int i13, String str20, String str21, String str22, int i14, int i15, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, str, (i14 & 8) != 0 ? null : str2, (i14 & 16) != 0 ? null : str3, (i14 & 32) != 0 ? null : str4, (i14 & 64) != 0 ? null : str5, (i14 & 128) != 0 ? null : str6, (i14 & 256) != 0 ? null : str7, (i14 & 512) != 0 ? null : str8, (i14 & 1024) != 0 ? 0 : i3, (i14 & 2048) != 0 ? null : str9, (i14 & 4096) != 0 ? null : str10, (i14 & 8192) != 0 ? null : str11, (i14 & 16384) != 0 ? null : str12, (32768 & i14) != 0 ? 0 : i4, (65536 & i14) != 0 ? 0 : i5, (131072 & i14) != 0 ? null : str13, (262144 & i14) != 0 ? null : str14, (524288 & i14) != 0 ? 0 : i6, (1048576 & i14) != 0 ? 0 : i7, (2097152 & i14) != 0 ? 0 : i8, (4194304 & i14) != 0 ? 0 : i9, (8388608 & i14) != 0 ? 0 : i10, (16777216 & i14) != 0 ? 0 : i11, (33554432 & i14) != 0 ? null : str15, (67108864 & i14) != 0 ? null : str16, (134217728 & i14) != 0 ? 0 : i12, (268435456 & i14) != 0 ? null : str17, (536870912 & i14) != 0 ? null : str18, (1073741824 & i14) != 0 ? null : str19, (i14 & Integer.MIN_VALUE) != 0 ? 0 : i13, (i15 & 1) != 0 ? null : str20, (i15 & 2) != 0 ? null : str21, (i15 & 4) != 0 ? null : str22);
        }

        public final int getId() {
            return this.id;
        }

        public final int getNo() {
            return this.no;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final String getPoster() {
            return this.poster;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getGenre() {
            return this.genre;
        }

        @Nullable
        public final String getDes() {
            return this.des;
        }

        @Nullable
        public final String getTags() {
            return this.tags;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        @Nullable
        public final String getTrailer() {
            return this.trailer;
        }

        public final int getTtype() {
            return this.ttype;
        }

        @Nullable
        public final String getBasic() {
            return this.basic;
        }

        @Nullable
        public final String getSd() {
            return this.sd;
        }

        @Nullable
        public final String getHd() {
            return this.hd;
        }

        @Nullable
        public final String getFhd() {
            return this.fhd;
        }

        public final int getShowId() {
            return this.showId;
        }

        public final int getLanguage() {
            return this.language;
        }

        @Nullable
        public final String getShowName() {
            return this.showName;
        }

        @Nullable
        public final String getLanguageName() {
            return this.languageName;
        }

        public final int getPremium() {
            return this.premium;
        }

        public final int getWfeathers() {
            return this.wfeathers;
        }

        public final int getBfeathers() {
            return this.bfeathers;
        }

        public final int getSfeathers() {
            return this.sfeathers;
        }

        public final int getTrending() {
            return this.trending;
        }

        public final int getSpecial() {
            return this.special;
        }

        @Nullable
        public final String getXPlayer2() {
            return this.xPlayer2;
        }

        @Nullable
        public final String getXPlayer3() {
            return this.xPlayer3;
        }

        public final int getLocked() {
            return this.locked;
        }

        @Nullable
        public final String getRating() {
            return this.rating;
        }

        @Nullable
        public final String getAvgRuntime() {
            return this.avgRuntime;
        }

        @Nullable
        public final String getAgeRating() {
            return this.ageRating;
        }

        public final int getTop10() {
            return this.top10;
        }

        @Nullable
        public final String getPlayCode() {
            return this.playCode;
        }

        @Nullable
        public final String getCreatedAt() {
            return this.createdAt;
        }

        @Nullable
        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }

    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lcom/cncverse/XonProvider$MoviesResponse;", "", "status", "", "lastUpdated", "", "movies", "", "Lcom/cncverse/XonProvider$Movie;", "<init>", "(ZLjava/lang/String;Ljava/util/List;)V", "getStatus", "()Z", "getLastUpdated", "()Ljava/lang/String;", "getMovies", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "XonProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MoviesResponse {

        @JsonProperty("last_updated")
        @NotNull
        private final String lastUpdated;

        @JsonProperty("movies")
        @NotNull
        private final List<Movie> movies;

        @JsonProperty("status")
        private final boolean status;

        public MoviesResponse() {
            this(false, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MoviesResponse copy$default(MoviesResponse moviesResponse, boolean z, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = moviesResponse.status;
            }
            if ((i & 2) != 0) {
                str = moviesResponse.lastUpdated;
            }
            if ((i & 4) != 0) {
                list = moviesResponse.movies;
            }
            return moviesResponse.copy(z, str, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getStatus() {
            return this.status;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLastUpdated() {
            return this.lastUpdated;
        }

        @NotNull
        public final List<Movie> component3() {
            return this.movies;
        }

        @NotNull
        public final MoviesResponse copy(@JsonProperty("status") boolean status, @JsonProperty("last_updated") @NotNull String lastUpdated, @JsonProperty("movies") @NotNull List<Movie> movies) {
            return new MoviesResponse(status, lastUpdated, movies);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MoviesResponse)) {
                return false;
            }
            MoviesResponse moviesResponse = (MoviesResponse) other;
            return this.status == moviesResponse.status && Intrinsics.areEqual(this.lastUpdated, moviesResponse.lastUpdated) && Intrinsics.areEqual(this.movies, moviesResponse.movies);
        }

        public int hashCode() {
            return (((XonProvider$MoviesResponse$$ExternalSyntheticBackport0.m(this.status) * 31) + this.lastUpdated.hashCode()) * 31) + this.movies.hashCode();
        }

        @NotNull
        public String toString() {
            return "MoviesResponse(status=" + this.status + ", lastUpdated=" + this.lastUpdated + ", movies=" + this.movies + ')';
        }

        public MoviesResponse(@JsonProperty("status") boolean status, @JsonProperty("last_updated") @NotNull String lastUpdated, @JsonProperty("movies") @NotNull List<Movie> list) {
            this.status = status;
            this.lastUpdated = lastUpdated;
            this.movies = list;
        }

        public /* synthetic */ MoviesResponse(boolean z, String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "" : str, (i & 4) != 0 ? CollectionsKt.emptyList() : list);
        }

        public final boolean getStatus() {
            return this.status;
        }

        @NotNull
        public final String getLastUpdated() {
            return this.lastUpdated;
        }

        @NotNull
        public final List<Movie> getMovies() {
            return this.movies;
        }
    }

    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\bW\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B¿\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0016\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0017\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u001a\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b \u0010!J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0006HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0003HÆ\u0003J\t\u0010S\u001a\u00020\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010V\u001a\u00020\u0003HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010Z\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0006HÆ\u0003JÁ\u0002\u0010\\\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\b\b\u0003\u0010\u0005\u001a\u00020\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0010\u001a\u00020\u00032\b\b\u0003\u0010\u0011\u001a\u00020\u00032\b\b\u0003\u0010\u0012\u001a\u00020\u00032\b\b\u0003\u0010\u0013\u001a\u00020\u00032\b\b\u0003\u0010\u0014\u001a\u00020\u00032\b\b\u0003\u0010\u0015\u001a\u00020\u00032\b\b\u0003\u0010\u0016\u001a\u00020\u00032\b\b\u0003\u0010\u0017\u001a\u00020\u00032\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0019\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u001a\u001a\u00020\u00032\n\b\u0003\u0010\u001b\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u001c\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u001d\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u001e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010]\u001a\u00020^2\b\u0010_\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010`\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010a\u001a\u00020\u0006HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010&R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010&R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010&R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u0016\u0010\u0010\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010#R\u0016\u0010\u0011\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010#R\u0016\u0010\u0012\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u0016\u0010\u0013\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010#R\u0016\u0010\u0014\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010#R\u0016\u0010\u0015\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010#R\u0016\u0010\u0016\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010#R\u0016\u0010\u0017\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u0010#R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010&R\u0016\u0010\u001a\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010#R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010&R\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010&R\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010&R\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010&R\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010&¨\u0006b"}, d2 = {"Lcom/cncverse/XonProvider$Episode;", "", "id", "", "no", "name", "", "thumb", "cover", "des", "tags", "type", "basic", "sd", "hd", "fhd", "seasonId", "showId", "language", "premium", "wfeathers", "bfeathers", "sfeathers", "trending", "aplayer1", "aplayer2", "locked", "playCode", "showName", "languageName", "seasonName", "updatedAt", "<init>", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIIIIIILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getNo", "getName", "()Ljava/lang/String;", "getThumb", "getCover", "getDes", "getTags", "getType", "getBasic", "getSd", "getHd", "getFhd", "getSeasonId", "getShowId", "getLanguage", "getPremium", "getWfeathers", "getBfeathers", "getSfeathers", "getTrending", "getAplayer1", "getAplayer2", "getLocked", "getPlayCode", "getShowName", "getLanguageName", "getSeasonName", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "copy", "equals", "", "other", "hashCode", "toString", "XonProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Episode {

        @JsonProperty("aplayer1")
        @Nullable
        private final String aplayer1;

        @JsonProperty("aplayer2")
        @Nullable
        private final String aplayer2;

        @JsonProperty("basic")
        @Nullable
        private final String basic;

        @JsonProperty("bfeathers")
        private final int bfeathers;

        @JsonProperty("cover")
        @Nullable
        private final String cover;

        @JsonProperty("des")
        @Nullable
        private final String des;

        @JsonProperty("fhd")
        @Nullable
        private final String fhd;

        @JsonProperty("hd")
        @Nullable
        private final String hd;

        @JsonProperty("id")
        private final int id;

        @JsonProperty("language")
        private final int language;

        @JsonProperty("languageName")
        @Nullable
        private final String languageName;

        @JsonProperty("locked")
        private final int locked;

        @JsonProperty("name")
        @NotNull
        private final String name;

        @JsonProperty("no")
        private final int no;

        @JsonProperty("play_code")
        @Nullable
        private final String playCode;

        @JsonProperty("premium")
        private final int premium;

        @JsonProperty("sd")
        @Nullable
        private final String sd;

        @JsonProperty("season_id")
        private final int seasonId;

        @JsonProperty("season_name")
        @Nullable
        private final String seasonName;

        @JsonProperty("sfeathers")
        private final int sfeathers;

        @JsonProperty("show_id")
        private final int showId;

        @JsonProperty("showName")
        @Nullable
        private final String showName;

        @JsonProperty("tags")
        @Nullable
        private final String tags;

        @JsonProperty("thumb")
        @Nullable
        private final String thumb;

        @JsonProperty("trending")
        private final int trending;

        @JsonProperty("type")
        @Nullable
        private final String type;

        @JsonProperty("updated_at")
        @Nullable
        private final String updatedAt;

        @JsonProperty("wfeathers")
        private final int wfeathers;

        public static /* synthetic */ Episode copy$default(Episode episode, int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str11, String str12, int i11, String str13, String str14, String str15, String str16, String str17, int i12, Object obj) {
            String str18;
            String str19;
            int i13 = (i12 & 1) != 0 ? episode.id : i;
            int i14 = (i12 & 2) != 0 ? episode.no : i2;
            String str20 = (i12 & 4) != 0 ? episode.name : str;
            String str21 = (i12 & 8) != 0 ? episode.thumb : str2;
            String str22 = (i12 & 16) != 0 ? episode.cover : str3;
            String str23 = (i12 & 32) != 0 ? episode.des : str4;
            String str24 = (i12 & 64) != 0 ? episode.tags : str5;
            String str25 = (i12 & 128) != 0 ? episode.type : str6;
            String str26 = (i12 & 256) != 0 ? episode.basic : str7;
            String str27 = (i12 & 512) != 0 ? episode.sd : str8;
            String str28 = (i12 & 1024) != 0 ? episode.hd : str9;
            String str29 = (i12 & 2048) != 0 ? episode.fhd : str10;
            int i15 = (i12 & 4096) != 0 ? episode.seasonId : i3;
            int i16 = (i12 & 8192) != 0 ? episode.showId : i4;
            int i17 = i13;
            int i18 = (i12 & 16384) != 0 ? episode.language : i5;
            int i19 = (i12 & 32768) != 0 ? episode.premium : i6;
            int i20 = (i12 & 65536) != 0 ? episode.wfeathers : i7;
            int i21 = (i12 & 131072) != 0 ? episode.bfeathers : i8;
            int i22 = (i12 & 262144) != 0 ? episode.sfeathers : i9;
            int i23 = (i12 & 524288) != 0 ? episode.trending : i10;
            String str30 = (i12 & 1048576) != 0 ? episode.aplayer1 : str11;
            String str31 = (i12 & 2097152) != 0 ? episode.aplayer2 : str12;
            int i24 = (i12 & 4194304) != 0 ? episode.locked : i11;
            String str32 = (i12 & 8388608) != 0 ? episode.playCode : str13;
            String str33 = (i12 & 16777216) != 0 ? episode.showName : str14;
            String str34 = (i12 & 33554432) != 0 ? episode.languageName : str15;
            String str35 = (i12 & 67108864) != 0 ? episode.seasonName : str16;
            if ((i12 & 134217728) != 0) {
                str19 = str35;
                str18 = episode.updatedAt;
            } else {
                str18 = str17;
                str19 = str35;
            }
            return episode.copy(i17, i14, str20, str21, str22, str23, str24, str25, str26, str27, str28, str29, i15, i16, i18, i19, i20, i21, i22, i23, str30, str31, i24, str32, str33, str34, str19, str18);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getSd() {
            return this.sd;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getHd() {
            return this.hd;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getFhd() {
            return this.fhd;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final int getSeasonId() {
            return this.seasonId;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final int getShowId() {
            return this.showId;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final int getLanguage() {
            return this.language;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final int getPremium() {
            return this.premium;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final int getWfeathers() {
            return this.wfeathers;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final int getBfeathers() {
            return this.bfeathers;
        }

        /* JADX INFO: renamed from: component19, reason: from getter */
        public final int getSfeathers() {
            return this.sfeathers;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getNo() {
            return this.no;
        }

        /* JADX INFO: renamed from: component20, reason: from getter */
        public final int getTrending() {
            return this.trending;
        }

        @Nullable
        /* JADX INFO: renamed from: component21, reason: from getter */
        public final String getAplayer1() {
            return this.aplayer1;
        }

        @Nullable
        /* JADX INFO: renamed from: component22, reason: from getter */
        public final String getAplayer2() {
            return this.aplayer2;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final int getLocked() {
            return this.locked;
        }

        @Nullable
        /* JADX INFO: renamed from: component24, reason: from getter */
        public final String getPlayCode() {
            return this.playCode;
        }

        @Nullable
        /* JADX INFO: renamed from: component25, reason: from getter */
        public final String getShowName() {
            return this.showName;
        }

        @Nullable
        /* JADX INFO: renamed from: component26, reason: from getter */
        public final String getLanguageName() {
            return this.languageName;
        }

        @Nullable
        /* JADX INFO: renamed from: component27, reason: from getter */
        public final String getSeasonName() {
            return this.seasonName;
        }

        @Nullable
        /* JADX INFO: renamed from: component28, reason: from getter */
        public final String getUpdatedAt() {
            return this.updatedAt;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getThumb() {
            return this.thumb;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getDes() {
            return this.des;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getTags() {
            return this.tags;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getBasic() {
            return this.basic;
        }

        @NotNull
        public final Episode copy(@JsonProperty("id") int id, @JsonProperty("no") int no, @JsonProperty("name") @NotNull String name, @JsonProperty("thumb") @Nullable String thumb, @JsonProperty("cover") @Nullable String cover, @JsonProperty("des") @Nullable String des, @JsonProperty("tags") @Nullable String tags, @JsonProperty("type") @Nullable String type, @JsonProperty("basic") @Nullable String basic, @JsonProperty("sd") @Nullable String sd, @JsonProperty("hd") @Nullable String hd, @JsonProperty("fhd") @Nullable String fhd, @JsonProperty("season_id") int seasonId, @JsonProperty("show_id") int showId, @JsonProperty("language") int language, @JsonProperty("premium") int premium, @JsonProperty("wfeathers") int wfeathers, @JsonProperty("bfeathers") int bfeathers, @JsonProperty("sfeathers") int sfeathers, @JsonProperty("trending") int trending, @JsonProperty("aplayer1") @Nullable String aplayer1, @JsonProperty("aplayer2") @Nullable String aplayer2, @JsonProperty("locked") int locked, @JsonProperty("play_code") @Nullable String playCode, @JsonProperty("showName") @Nullable String showName, @JsonProperty("languageName") @Nullable String languageName, @JsonProperty("season_name") @Nullable String seasonName, @JsonProperty("updated_at") @Nullable String updatedAt) {
            return new Episode(id, no, name, thumb, cover, des, tags, type, basic, sd, hd, fhd, seasonId, showId, language, premium, wfeathers, bfeathers, sfeathers, trending, aplayer1, aplayer2, locked, playCode, showName, languageName, seasonName, updatedAt);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Episode)) {
                return false;
            }
            Episode episode = (Episode) other;
            return this.id == episode.id && this.no == episode.no && Intrinsics.areEqual(this.name, episode.name) && Intrinsics.areEqual(this.thumb, episode.thumb) && Intrinsics.areEqual(this.cover, episode.cover) && Intrinsics.areEqual(this.des, episode.des) && Intrinsics.areEqual(this.tags, episode.tags) && Intrinsics.areEqual(this.type, episode.type) && Intrinsics.areEqual(this.basic, episode.basic) && Intrinsics.areEqual(this.sd, episode.sd) && Intrinsics.areEqual(this.hd, episode.hd) && Intrinsics.areEqual(this.fhd, episode.fhd) && this.seasonId == episode.seasonId && this.showId == episode.showId && this.language == episode.language && this.premium == episode.premium && this.wfeathers == episode.wfeathers && this.bfeathers == episode.bfeathers && this.sfeathers == episode.sfeathers && this.trending == episode.trending && Intrinsics.areEqual(this.aplayer1, episode.aplayer1) && Intrinsics.areEqual(this.aplayer2, episode.aplayer2) && this.locked == episode.locked && Intrinsics.areEqual(this.playCode, episode.playCode) && Intrinsics.areEqual(this.showName, episode.showName) && Intrinsics.areEqual(this.languageName, episode.languageName) && Intrinsics.areEqual(this.seasonName, episode.seasonName) && Intrinsics.areEqual(this.updatedAt, episode.updatedAt);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((((((((((((((((((((((((this.id * 31) + this.no) * 31) + this.name.hashCode()) * 31) + (this.thumb == null ? 0 : this.thumb.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.des == null ? 0 : this.des.hashCode())) * 31) + (this.tags == null ? 0 : this.tags.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.basic == null ? 0 : this.basic.hashCode())) * 31) + (this.sd == null ? 0 : this.sd.hashCode())) * 31) + (this.hd == null ? 0 : this.hd.hashCode())) * 31) + (this.fhd == null ? 0 : this.fhd.hashCode())) * 31) + this.seasonId) * 31) + this.showId) * 31) + this.language) * 31) + this.premium) * 31) + this.wfeathers) * 31) + this.bfeathers) * 31) + this.sfeathers) * 31) + this.trending) * 31) + (this.aplayer1 == null ? 0 : this.aplayer1.hashCode())) * 31) + (this.aplayer2 == null ? 0 : this.aplayer2.hashCode())) * 31) + this.locked) * 31) + (this.playCode == null ? 0 : this.playCode.hashCode())) * 31) + (this.showName == null ? 0 : this.showName.hashCode())) * 31) + (this.languageName == null ? 0 : this.languageName.hashCode())) * 31) + (this.seasonName == null ? 0 : this.seasonName.hashCode())) * 31) + (this.updatedAt != null ? this.updatedAt.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Episode(id=").append(this.id).append(", no=").append(this.no).append(", name=").append(this.name).append(", thumb=").append(this.thumb).append(", cover=").append(this.cover).append(", des=").append(this.des).append(", tags=").append(this.tags).append(", type=").append(this.type).append(", basic=").append(this.basic).append(", sd=").append(this.sd).append(", hd=").append(this.hd).append(", fhd=");
            sb.append(this.fhd).append(", seasonId=").append(this.seasonId).append(", showId=").append(this.showId).append(", language=").append(this.language).append(", premium=").append(this.premium).append(", wfeathers=").append(this.wfeathers).append(", bfeathers=").append(this.bfeathers).append(", sfeathers=").append(this.sfeathers).append(", trending=").append(this.trending).append(", aplayer1=").append(this.aplayer1).append(", aplayer2=").append(this.aplayer2).append(", locked=").append(this.locked);
            sb.append(", playCode=").append(this.playCode).append(", showName=").append(this.showName).append(", languageName=").append(this.languageName).append(", seasonName=").append(this.seasonName).append(", updatedAt=").append(this.updatedAt).append(')');
            return sb.toString();
        }

        public Episode(@JsonProperty("id") int id, @JsonProperty("no") int no, @JsonProperty("name") @NotNull String name, @JsonProperty("thumb") @Nullable String thumb, @JsonProperty("cover") @Nullable String cover, @JsonProperty("des") @Nullable String des, @JsonProperty("tags") @Nullable String tags, @JsonProperty("type") @Nullable String type, @JsonProperty("basic") @Nullable String basic, @JsonProperty("sd") @Nullable String sd, @JsonProperty("hd") @Nullable String hd, @JsonProperty("fhd") @Nullable String fhd, @JsonProperty("season_id") int seasonId, @JsonProperty("show_id") int showId, @JsonProperty("language") int language, @JsonProperty("premium") int premium, @JsonProperty("wfeathers") int wfeathers, @JsonProperty("bfeathers") int bfeathers, @JsonProperty("sfeathers") int sfeathers, @JsonProperty("trending") int trending, @JsonProperty("aplayer1") @Nullable String aplayer1, @JsonProperty("aplayer2") @Nullable String aplayer2, @JsonProperty("locked") int locked, @JsonProperty("play_code") @Nullable String playCode, @JsonProperty("showName") @Nullable String showName, @JsonProperty("languageName") @Nullable String languageName, @JsonProperty("season_name") @Nullable String seasonName, @JsonProperty("updated_at") @Nullable String updatedAt) {
            this.id = id;
            this.no = no;
            this.name = name;
            this.thumb = thumb;
            this.cover = cover;
            this.des = des;
            this.tags = tags;
            this.type = type;
            this.basic = basic;
            this.sd = sd;
            this.hd = hd;
            this.fhd = fhd;
            this.seasonId = seasonId;
            this.showId = showId;
            this.language = language;
            this.premium = premium;
            this.wfeathers = wfeathers;
            this.bfeathers = bfeathers;
            this.sfeathers = sfeathers;
            this.trending = trending;
            this.aplayer1 = aplayer1;
            this.aplayer2 = aplayer2;
            this.locked = locked;
            this.playCode = playCode;
            this.showName = showName;
            this.languageName = languageName;
            this.seasonName = seasonName;
            this.updatedAt = updatedAt;
        }

        public /* synthetic */ Episode(int i, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str11, String str12, int i11, String str13, String str14, String str15, String str16, String str17, int i12, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, str, (i12 & 8) != 0 ? null : str2, (i12 & 16) != 0 ? null : str3, (i12 & 32) != 0 ? null : str4, (i12 & 64) != 0 ? null : str5, (i12 & 128) != 0 ? null : str6, (i12 & 256) != 0 ? null : str7, (i12 & 512) != 0 ? null : str8, (i12 & 1024) != 0 ? null : str9, (i12 & 2048) != 0 ? null : str10, (i12 & 4096) != 0 ? 0 : i3, (i12 & 8192) != 0 ? 0 : i4, (i12 & 16384) != 0 ? 0 : i5, (32768 & i12) != 0 ? 0 : i6, (65536 & i12) != 0 ? 0 : i7, (131072 & i12) != 0 ? 0 : i8, (262144 & i12) != 0 ? 0 : i9, (524288 & i12) != 0 ? 0 : i10, (1048576 & i12) != 0 ? null : str11, (2097152 & i12) != 0 ? null : str12, (4194304 & i12) != 0 ? 0 : i11, (8388608 & i12) != 0 ? null : str13, (16777216 & i12) != 0 ? null : str14, (33554432 & i12) != 0 ? null : str15, (67108864 & i12) != 0 ? null : str16, (i12 & 134217728) != 0 ? null : str17);
        }

        public final int getId() {
            return this.id;
        }

        public final int getNo() {
            return this.no;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @Nullable
        public final String getThumb() {
            return this.thumb;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getDes() {
            return this.des;
        }

        @Nullable
        public final String getTags() {
            return this.tags;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        @Nullable
        public final String getBasic() {
            return this.basic;
        }

        @Nullable
        public final String getSd() {
            return this.sd;
        }

        @Nullable
        public final String getHd() {
            return this.hd;
        }

        @Nullable
        public final String getFhd() {
            return this.fhd;
        }

        public final int getSeasonId() {
            return this.seasonId;
        }

        public final int getShowId() {
            return this.showId;
        }

        public final int getLanguage() {
            return this.language;
        }

        public final int getPremium() {
            return this.premium;
        }

        public final int getWfeathers() {
            return this.wfeathers;
        }

        public final int getBfeathers() {
            return this.bfeathers;
        }

        public final int getSfeathers() {
            return this.sfeathers;
        }

        public final int getTrending() {
            return this.trending;
        }

        @Nullable
        public final String getAplayer1() {
            return this.aplayer1;
        }

        @Nullable
        public final String getAplayer2() {
            return this.aplayer2;
        }

        public final int getLocked() {
            return this.locked;
        }

        @Nullable
        public final String getPlayCode() {
            return this.playCode;
        }

        @Nullable
        public final String getShowName() {
            return this.showName;
        }

        @Nullable
        public final String getLanguageName() {
            return this.languageName;
        }

        @Nullable
        public final String getSeasonName() {
            return this.seasonName;
        }

        @Nullable
        public final String getUpdatedAt() {
            return this.updatedAt;
        }
    }

    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/XonProvider$EpisodesResponse;", "", "episodes", "", "Lcom/cncverse/XonProvider$Episode;", "<init>", "(Ljava/util/List;)V", "getEpisodes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "XonProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class EpisodesResponse {

        @JsonProperty("episodes")
        @NotNull
        private final List<Episode> episodes;

        /* JADX WARN: Illegal instructions before constructor call */
        public EpisodesResponse() {
            List list = null;
            this(list, 1, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ EpisodesResponse copy$default(EpisodesResponse episodesResponse, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = episodesResponse.episodes;
            }
            return episodesResponse.copy(list);
        }

        @NotNull
        public final List<Episode> component1() {
            return this.episodes;
        }

        @NotNull
        public final EpisodesResponse copy(@JsonProperty("episodes") @NotNull List<Episode> episodes) {
            return new EpisodesResponse(episodes);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof EpisodesResponse) && Intrinsics.areEqual(this.episodes, ((EpisodesResponse) other).episodes);
        }

        public int hashCode() {
            return this.episodes.hashCode();
        }

        @NotNull
        public String toString() {
            return "EpisodesResponse(episodes=" + this.episodes + ')';
        }

        public EpisodesResponse(@JsonProperty("episodes") @NotNull List<Episode> list) {
            this.episodes = list;
        }

        public /* synthetic */ EpisodesResponse(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
        }

        @NotNull
        public final List<Episode> getEpisodes() {
            return this.episodes;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchRemoteConfig(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.cncverse.XonProvider.C00031
            if (r0 == 0) goto L14
            r0 = r11
            com.cncverse.XonProvider$fetchRemoteConfig$1 r0 = (com.cncverse.XonProvider.C00031) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.cncverse.XonProvider$fetchRemoteConfig$1 r0 = new com.cncverse.XonProvider$fetchRemoteConfig$1
            r0.<init>(r11)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L34;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Exception -> L32
            r3 = r1
            goto L43
        L32:
            r2 = move-exception
            goto L78
        L34:
            kotlin.ResultKt.throwOnFailure(r1)
            com.cncverse.XonFirebaseRemoteConfigFetcher r3 = com.cncverse.XonFirebaseRemoteConfigFetcher.INSTANCE     // Catch: java.lang.Exception -> L32
            r0.label = r4     // Catch: java.lang.Exception -> L32
            java.lang.Object r3 = r3.getAllConfig(r0)     // Catch: java.lang.Exception -> L32
            if (r3 != r2) goto L43
            return r2
        L43:
            kotlin.Triple r3 = (kotlin.Triple) r3     // Catch: java.lang.Exception -> L32
            java.lang.Object r2 = r3.component1()     // Catch: java.lang.Exception -> L32
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Exception -> L32
            java.lang.Object r5 = r3.component2()     // Catch: java.lang.Exception -> L32
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Exception -> L32
            java.lang.Object r3 = r3.component3()     // Catch: java.lang.Exception -> L32
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L32
            if (r2 == 0) goto L5e
            r6 = r2
            r7 = 0
            r10.setMainUrl(r6)     // Catch: java.lang.Exception -> L32
        L5e:
            if (r5 == 0) goto L64
            r6 = r5
            r7 = 0
            r10.apiKey = r6     // Catch: java.lang.Exception -> L32
        L64:
            if (r3 == 0) goto L6a
            r6 = r3
            r7 = 0
            r10.callerName = r6     // Catch: java.lang.Exception -> L32
        L6a:
            r10.configFetched = r4     // Catch: java.lang.Exception -> L32
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> L32
            r4 = 3600000(0x36ee80, float:5.044674E-39)
            long r8 = (long) r4     // Catch: java.lang.Exception -> L32
            long r6 = r6 + r8
            r10.configExpireTime = r6     // Catch: java.lang.Exception -> L32
            goto L94
        L78:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Xon Provider: Failed to fetch remote config - "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r4 = r2.getMessage()
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.io.PrintStream r4 = java.lang.System.out
            r4.println(r3)
        L94:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.fetchRemoteConfig(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.fasterxml.jackson.databind.RuntimeJsonMappingException */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0106 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x020a A[Catch: Exception -> 0x0261, TryCatch #6 {Exception -> 0x0261, blocks: (B:63:0x01ea, B:65:0x020a, B:67:0x0232, B:69:0x0245, B:70:0x0252, B:71:0x0253), top: B:97:0x01ea }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0253 A[Catch: Exception -> 0x0261, TRY_LEAVE, TryCatch #6 {Exception -> 0x0261, blocks: (B:63:0x01ea, B:65:0x020a, B:67:0x0232, B:69:0x0245, B:70:0x0252, B:71:0x0253), top: B:97:0x01ea }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshCache(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r30) throws com.fasterxml.jackson.databind.RuntimeJsonMappingException {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.refreshCache(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatUrl(String url) {
        if (StringsKt.startsWith$default(url, "http://", false, 2, (Object) null) || StringsKt.startsWith$default(url, "https://", false, 2, (Object) null)) {
            return url;
        }
        return "https://archive.org/download/" + url;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String bestPoster(Movie $this$bestPoster) {
        String poster = $this$bestPoster.getPoster();
        if (!(poster == null || poster.length() == 0)) {
            return formatUrl($this$bestPoster.getPoster());
        }
        String cover = $this$bestPoster.getCover();
        return !(cover == null || cover.length() == 0) ? formatUrl($this$bestPoster.getCover()) : "";
    }

    private final String displayName(Movie $this$displayName) {
        String languageName = $this$displayName.getLanguageName();
        if (languageName == null || languageName.length() == 0) {
            String name = $this$displayName.getName();
            return name == null ? "" : name;
        }
        StringBuilder sb = new StringBuilder();
        String name2 = $this$displayName.getName();
        return sb.append(name2 != null ? name2 : "").append(" (").append($this$displayName.getLanguageName()).append(')').toString();
    }

    private final String displayName(Episode $this$displayName) {
        String showName = $this$displayName.getShowName();
        if (!(showName == null || showName.length() == 0)) {
            String languageName = $this$displayName.getLanguageName();
            if (!(languageName == null || languageName.length() == 0)) {
                StringBuilder sbAppend = new StringBuilder().append($this$displayName.getShowName()).append(" – ");
                String name = $this$displayName.getName();
                return sbAppend.append(name != null ? name : "").append(" (").append($this$displayName.getLanguageName()).append(')').toString();
            }
        }
        String name2 = $this$displayName.getName();
        return name2 == null ? "" : name2;
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r37, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r38, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r39) {
        /*
            Method dump skipped, instruction units count: 1030
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$1$0(XonProvider this$0, Movie $movie, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl(this$0.bestPoster($movie));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$3$0(Episode $ep, XonProvider this$0, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        String thumb = $ep.getThumb();
        $this$newTvSeriesSearchResponse.setPosterUrl(!(thumb == null || thumb.length() == 0) ? this$0.formatUrl($ep.getThumb()) : "");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$4$0(Episode $ep, XonProvider this$0, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        String thumb = $ep.getThumb();
        $this$newTvSeriesSearchResponse.setPosterUrl(!(thumb == null || thumb.length() == 0) ? this$0.formatUrl($ep.getThumb()) : "");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$6$0$0(XonProvider this$0, Movie $movie, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl(this$0.bestPoster($movie));
        return Unit.INSTANCE;
    }

    private final Pair<String, String> extractLanguageFromQuery(String query) {
        Object element$iv;
        boolean z;
        Iterable tokens = new Regex("\\s+").split(StringsKt.trim(query).toString(), 0);
        Iterable $this$firstOrNull$iv = tokens;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            String token = (String) element$iv;
            Iterable $this$any$iv = this.knownLanguages;
            if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
                Iterator it2 = $this$any$iv.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        Object element$iv2 = it2.next();
                        String it3 = (String) element$iv2;
                        if (StringsKt.equals(it3, token, true)) {
                            z = true;
                            break;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
            } else {
                z = false;
            }
            if (z) {
                break;
            }
        }
        String langToken = (String) element$iv;
        if (langToken == null) {
            return new Pair<>((Object) null, StringsKt.trim(query).toString());
        }
        Iterable $this$filterNot$iv = tokens;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filterNot$iv) {
            String it4 = (String) element$iv$iv;
            if (!StringsKt.equals(it4, langToken, true)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        String remaining = StringsKt.trim(CollectionsKt.joinToString$default((List) destination$iv$iv, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)).toString();
        String lowerCase = langToken.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return new Pair<>(lowerCase, remaining);
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0219 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x011f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0223 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0116 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01d3  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r29, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r30) {
        /*
            Method dump skipped, instruction units count: 650
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$1$0(XonProvider this$0, Movie $movie, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl(this$0.bestPoster($movie));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$3$0(Episode $ep, XonProvider this$0, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        String thumb = $ep.getThumb();
        $this$newTvSeriesSearchResponse.setPosterUrl(!(thumb == null || thumb.length() == 0) ? this$0.formatUrl($ep.getThumb()) : "");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r30, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r31) {
        /*
            Method dump skipped, instruction units count: 1052
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider$load$2", f = "XonProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nXonProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 XonProvider.kt\ncom/cncverse/XonProvider$load$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,780:1\n1#2:781\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Movie $movie;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(Movie movie, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$movie = movie;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = XonProvider.this.new AnonymousClass2(this.$movie, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return create(movieLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            String strTake;
            MovieLoadResponse $this$newMovieLoadResponse = (MovieLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newMovieLoadResponse.setPosterUrl(XonProvider.this.bestPoster(this.$movie));
                    Movie movie = this.$movie;
                    StringBuilder $this$invokeSuspend_u24lambda_u240 = new StringBuilder();
                    String it = movie.getDes();
                    if (it != null) {
                        $this$invokeSuspend_u24lambda_u240.append(it);
                        $this$invokeSuspend_u24lambda_u240.append("\n\n");
                    }
                    String rating = movie.getRating();
                    if (!(rating == null || rating.length() == 0)) {
                        $this$invokeSuspend_u24lambda_u240.append("⭐ " + movie.getRating() + '\n');
                    }
                    String avgRuntime = movie.getAvgRuntime();
                    if (!(avgRuntime == null || avgRuntime.length() == 0)) {
                        $this$invokeSuspend_u24lambda_u240.append("⏱ " + movie.getAvgRuntime() + '\n');
                    }
                    String ageRating = movie.getAgeRating();
                    if (!(ageRating == null || ageRating.length() == 0)) {
                        $this$invokeSuspend_u24lambda_u240.append("👶 " + movie.getAgeRating() + '\n');
                    }
                    String languageName = movie.getLanguageName();
                    if (!(languageName == null || languageName.length() == 0)) {
                        $this$invokeSuspend_u24lambda_u240.append("🌐 " + movie.getLanguageName());
                    }
                    $this$newMovieLoadResponse.setPlot($this$invokeSuspend_u24lambda_u240.toString());
                    String createdAt = this.$movie.getCreatedAt();
                    $this$newMovieLoadResponse.setYear((createdAt == null || (strTake = StringsKt.take(createdAt, 4)) == null) ? null : StringsKt.toIntOrNull(strTake));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable load$lambda$3(Episode it) {
        return Integer.valueOf(it.getSeasonId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable load$lambda$4(Episode it) {
        return Integer.valueOf(it.getNo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$7$0(Episode $e, Map $seasonNoMap, XonProvider this$0, com.lagradost.cloudstream3.Episode $this$newEpisode) {
        $this$newEpisode.setName($e.getName());
        $this$newEpisode.setSeason((Integer) $seasonNoMap.get(Integer.valueOf($e.getSeasonId())));
        $this$newEpisode.setEpisode(Integer.valueOf($e.getNo()));
        String thumb = $e.getThumb();
        $this$newEpisode.setPosterUrl(!(thumb == null || thumb.length() == 0) ? this$0.formatUrl($e.getThumb()) : "");
        String des = $e.getDes();
        String str = null;
        if (des != null) {
            String str2 = des;
            if (!(str2.length() == 0)) {
                str = str2;
            }
            str = str;
        }
        $this$newEpisode.setDescription(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$load$3, reason: invalid class name */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider$load$3", f = "XonProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Episode $ep;
        final /* synthetic */ String $langLabel;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Episode episode, String str, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$ep = episode;
            this.$langLabel = str;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = XonProvider.this.new AnonymousClass3(this.$ep, this.$langLabel, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return create(tvSeriesLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object next;
            String url;
            TvSeriesLoadResponse $this$newTvSeriesLoadResponse = (TvSeriesLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    List list = XonProvider.this.cachedMovies;
                    Episode episode = this.$ep;
                    Iterator it = list.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            next = it.next();
                            Movie it2 = (Movie) next;
                            if (it2.getShowId() == episode.getShowId() && it2.getLanguage() == episode.getLanguage()) {
                            }
                        } else {
                            next = null;
                        }
                    }
                    Movie matchedMovie = (Movie) next;
                    if (matchedMovie == null || (url = XonProvider.this.bestPoster(matchedMovie)) == null) {
                        String thumb = this.$ep.getThumb();
                        url = !(thumb == null || thumb.length() == 0) ? XonProvider.this.formatUrl(this.$ep.getThumb()) : "";
                    }
                    $this$newTvSeriesLoadResponse.setPosterUrl(url);
                    $this$newTvSeriesLoadResponse.setPlot(this.$langLabel.length() > 0 ? "Language: " + this.$langLabel : null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x040b  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02d6  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r28, boolean r29, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r30, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r32) {
        /*
            Method dump skipped, instruction units count: 1216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addVideoLinks(java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instruction units count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.XonProvider.addVideoLinks(java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.XonProvider$makeLink$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: XonProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.XonProvider$makeLink$2", f = "XonProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00072 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $quality;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00072(int i, Continuation<? super C00072> continuation) {
            super(2, continuation);
            this.$quality = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00072 = XonProvider.this.new C00072(this.$quality, continuation);
            c00072.L$0 = obj;
            return c00072;
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
                    $this$newExtractorLink.setReferer(XonProvider.this.getMainUrl());
                    $this$newExtractorLink.setQuality(this.$quality);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object makeLink(String label, String url, int quality, Continuation<? super ExtractorLink> continuation) {
        return ExtractorApiKt.newExtractorLink(getName(), getName() + " - " + label, url, ExtractorLinkType.VIDEO, new C00072(quality, null), continuation);
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
