package com.cncverse;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: MovieLinkBDProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nMovieLinkBDProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MovieLinkBDProvider.kt\ncom/cncverse/MovieLinkBDProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 6 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,556:1\n1#2:557\n1#2:577\n1915#3,2:558\n1915#3,2:560\n1915#3:562\n1916#3:565\n1642#3,10:566\n1915#3:576\n1916#3:578\n1652#3:579\n1915#3:580\n1916#3:602\n1915#3,2:605\n1915#3,2:607\n296#3,2:609\n1088#4,2:563\n383#5,7:581\n383#5,7:588\n383#5,7:595\n221#6,2:603\n*S KotlinDebug\n*F\n+ 1 MovieLinkBDProvider.kt\ncom/cncverse/MovieLinkBDProvider\n*L\n280#1:577\n139#1:558,2\n164#1:560,2\n190#1:562\n190#1:565\n280#1:566,10\n280#1:576\n280#1:578\n280#1:579\n306#1:580\n306#1:602\n386#1:605,2\n471#1:607,2\n242#1:609,2\n195#1:563,2\n320#1:581,7\n336#1:588,7\n339#1:595,7\n344#1:603,2\n*E\n"})
public final class MovieLinkBDProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String FALLBACK_URL = "https://movielinkbd.one";

    @NotNull

    @Nullable
    private static Context appContext;
    private final boolean hasQuickSearch;

    @Nullable
    private volatile String resolvedBase;

    @NotNull
    private String mainUrl = FALLBACK_URL;

    @NotNull
    private String name = "MovieLinkBD";

    @NotNull
    private String lang = "bn";
    private final boolean hasMainPage = true;
    private final boolean hasDownloadSupport = true;

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("/", "Recently Updated"), TuplesKt.to("/type/movies", "All Movies"), TuplesKt.to("/type/series", "All Web Series"), TuplesKt.to("/language/hindi", "Hindi Movies"), TuplesKt.to("/language/bangla", "Bangla Movies"), TuplesKt.to("/language/bangla-dubbed", "Bangla Dubbed"), TuplesKt.to("/language/dual-audio", "Dual Audio"), TuplesKt.to("/language/english", "English"), TuplesKt.to("/southIndian", "South Indian"), TuplesKt.to("/language/korean", "Korean"), TuplesKt.to("/anime", "Anime Zone"), TuplesKt.to("/drama", "K/J/C Drama"), TuplesKt.to("/ongoing", "Ongoing Series"), TuplesKt.to("/genre/action", "Action"), TuplesKt.to("/genre/thriller", "Thriller"), TuplesKt.to("/genre/horror", "Horror"), TuplesKt.to("/genre/romance", "Romance"), TuplesKt.to("/category/wwe", "WWE")});

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries, TvType.AsianDrama, TvType.AnimeMovie, TvType.Anime});

    @NotNull
    private final Map<String, String> headers = MapsKt.mapOf(new Pair[]{TuplesKt.to("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"), TuplesKt.to("Accept-Language", "en-US,en;q=0.9")});

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$getBase$1, reason: invalid class name */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {}, l = {91}, m = "getBase", n = {}, nl = {95}, s = {}, v = 2)
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
            return MovieLinkBDProvider.this.getBase((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {0, 0, 1, 1, 1, 1, 1}, l = {108, 118}, m = "getMainPage", n = {"request", "page", "request", "base", "path", "url", "page"}, nl = {109, 119}, s = {"L$0", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieLinkBDProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {224, 290, 353}, m = "load", n = {"url", "url", "doc", "rawTitle", "year", "poster", "plot", "genre", "cast", "language", "rating", "fullPlot", "linkAnchors", "linksData", "isSeries", "url", "doc", "rawTitle", "year", "poster", "plot", "genre", "cast", "language", "rating", "fullPlot", "linkAnchors", "episodesData", "isSeries"}, nl = {227, 299, -1}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "I$0"}, v = 2)
    static final class C00011 extends ContinuationImpl {
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

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieLinkBDProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {395, 399, 405, 419}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "$this$forEach$iv", "element$iv", "item", "qualityLabel", "parts", "linkUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-MovieLinkBDProvider$loadLinks$3", "data", "subtitleCallback", "callback", "$this$forEach$iv", "element$iv", "item", "qualityLabel", "parts", "linkUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-MovieLinkBDProvider$loadLinks$3", "data", "subtitleCallback", "callback", "$this$forEach$iv", "element$iv", "item", "qualityLabel", "parts", "linkUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-MovieLinkBDProvider$loadLinks$3", "quality", "data", "subtitleCallback", "callback", "$this$forEach$iv", "element$iv", "item", "qualityLabel", "parts", "linkUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-MovieLinkBDProvider$loadLinks$3"}, nl = {398, 402, 404, 422}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
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

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieLinkBDProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$resolveGetLink$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {434, 443, 457, 476}, m = "resolveGetLink", n = {"getLinkUrl", "qualityLabel", "callback", "getLinkUrl", "qualityLabel", "callback", "doc", "fileAnchor", "href", "fileUrl", "getLinkUrl", "qualityLabel", "callback", "doc", "fileAnchor", "href", "fileUrl", "fileHtml", "srcRegex", "srcMatch", "srcUrl", "quality", "isM3u8", "getLinkUrl", "qualityLabel", "callback", "doc", "fileAnchor", "href", "fileUrl", "fileHtml", "srcRegex", "srcMatch", "$this$forEach$iv", "element$iv", "a", "href", "$i$f$forEach", "$i$a$-forEach-MovieLinkBDProvider$resolveGetLink$3"}, nl = {437, 446, 456, 482}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "I$0", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$13", "L$14", "I$0", "I$1"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
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
            return MovieLinkBDProvider.this.resolveGetLink(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$resolveGetWatch$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {493, 501, 514}, m = "resolveGetWatch", n = {"getWatchUrl", "qualityLabel", "callback", "getWatchUrl", "qualityLabel", "callback", "doc", "videoSrc", "quality", "getWatchUrl", "qualityLabel", "callback", "doc", "videoSrc", "quality"}, nl = {495, 500, 513}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0"}, v = 2)
    static final class C00051 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C00051(Continuation<? super C00051> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieLinkBDProvider.this.resolveGetWatch(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider", f = "MovieLinkBDProvider.kt", i = {0, 1, 1}, l = {125, 126}, m = "search", n = {"query", "query", "base"}, nl = {126, 127}, s = {"L$0", "L$0", "L$1"}, v = 2)
    static final class C00071 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00071(Continuation<? super C00071> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieLinkBDProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/cncverse/MovieLinkBDProvider$Companion;", "", "<init>", "()V", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "setAppContext", "(Landroid/content/Context;)V", "FALLBACK_URL", "", "OMG10", "lastBrowserOpenMs", "", "BROWSER_DEBOUNCE_MS", "MovieLinkBDProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getAppContext() {
            return MovieLinkBDProvider.appContext;
        }

        public final void setAppContext(@Nullable Context context) {
            MovieLinkBDProvider.appContext = context;
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

    @NotNull
    public String getLang() {
        return this.lang;
    }

    public void setLang(@NotNull String str) {
        this.lang = str;
    }

    public boolean getHasMainPage() {
        return this.hasMainPage;
    }

    public boolean getHasDownloadSupport() {
        return this.hasDownloadSupport;
    }

    public boolean getHasQuickSearch() {
        return this.hasQuickSearch;
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getBase(kotlin.coroutines.Continuation<? super java.lang.String> r23) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.getBase(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0138 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r28, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r29, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r30) {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00ce A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r25, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r26) {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0296 A[PHI: r8
  0x0296: PHI (r8v21 'it' java.lang.String) = (r8v13 'it' java.lang.String), (r8v18 'it' java.lang.String), (r8v30 'it' java.lang.String) binds: [B:111:0x0268, B:119:0x028f, B:103:0x0241] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03b4 A[LOOP:3: B:156:0x0388->B:168:0x03b4, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03b2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e8 A[PHI: r3
  0x00e8: PHI (r3v19 'it' java.lang.String) = (r3v17 'it' java.lang.String), (r3v36 'it' java.lang.String) binds: [B:38:0x00de, B:30:0x00b8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0143  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.lagradost.cloudstream3.SearchResponse> parseMovieCards(org.jsoup.nodes.Document r38, java.lang.String r39) {
        /*
            Method dump skipped, instruction units count: 1053
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.parseMovieCards(org.jsoup.nodes.Document, java.lang.String):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit parseMovieCards$lambda$0$4(String $poster, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($poster);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit parseMovieCards$lambda$1$5(String $poster, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($poster.length() > 0 ? $poster : null);
        return Unit.INSTANCE;
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX WARN: Code restructure failed: missing block: B:178:0x0624, code lost:
    
        r0 = r38;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02b9  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r47, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r48) {
        /*
            Method dump skipped, instruction units count: 1980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final String load$metaVal(Document doc, String label) {
        Object element$iv;
        String strText;
        String strSubstringAfter$default;
        Iterable $this$firstOrNull$iv = doc.select("li, p, span, div");
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                element$iv = null;
                break;
            }
            element$iv = it.next();
            Element el = (Element) element$iv;
            String text = el.text();
            boolean z = true;
            if (!StringsKt.contains(text, label, true) || !StringsKt.contains(StringsKt.substringBefore$default(text, ":", (String) null, 2, (Object) null), label, true)) {
                z = false;
            }
            if (z) {
                break;
            }
        }
        Element element = (Element) element$iv;
        if (element == null || (strText = element.text()) == null || (strSubstringAfter$default = StringsKt.substringAfter$default(strText, ":", (String) null, 2, (Object) null)) == null) {
            return null;
        }
        return StringsKt.trim(strSubstringAfter$default).toString();
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider$load$2", f = "MovieLinkBDProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nMovieLinkBDProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MovieLinkBDProvider.kt\ncom/cncverse/MovieLinkBDProvider$load$2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,556:1\n1#2:557\n*E\n"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fullPlot;
        final /* synthetic */ String $poster;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Integer num, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$fullPlot = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$poster, this.$year, this.$fullPlot, continuation);
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
                    $this$newMovieLoadResponse.setPosterUrl(this.$poster);
                    $this$newMovieLoadResponse.setYear(this.$year);
                    String it = this.$fullPlot;
                    if (!(it.length() > 0)) {
                        it = null;
                    }
                    $this$newMovieLoadResponse.setPlot(it);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$7$0(Integer $epNum, Episode $this$newEpisode) {
        $this$newEpisode.setName("Episode " + $epNum);
        $this$newEpisode.setSeason(1);
        $this$newEpisode.setEpisode($epNum);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$load$5, reason: invalid class name */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider$load$5", f = "MovieLinkBDProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nMovieLinkBDProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MovieLinkBDProvider.kt\ncom/cncverse/MovieLinkBDProvider$load$5\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,556:1\n1#2:557\n*E\n"})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fullPlot;
        final /* synthetic */ String $poster;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(String str, Integer num, String str2, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$fullPlot = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass5 = new AnonymousClass5(this.$poster, this.$year, this.$fullPlot, continuation);
            anonymousClass5.L$0 = obj;
            return anonymousClass5;
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
                    $this$newTvSeriesLoadResponse.setPosterUrl(this.$poster);
                    $this$newTvSeriesLoadResponse.setYear(this.$year);
                    String it = this.$fullPlot;
                    if (!(it.length() > 0)) {
                        it = null;
                    }
                    $this$newTvSeriesLoadResponse.setPlot(it);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x04ec  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:91:0x04ad -> B:92:0x04c7). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:93:0x04d2 -> B:94:0x04e8). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r26, boolean r27, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r28, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r29, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r30) {
        /*
            Method dump skipped, instruction units count: 1284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0296 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x01cc A[Catch: Exception -> 0x01cf, TRY_ENTER, TRY_LEAVE, TryCatch #0 {Exception -> 0x01cf, blocks: (B:40:0x01cc, B:53:0x020e), top: B:138:0x01ca }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01dc A[Catch: Exception -> 0x053e, TRY_ENTER, TryCatch #8 {Exception -> 0x053e, blocks: (B:38:0x01be, B:44:0x01dc, B:48:0x0200, B:55:0x0211, B:47:0x01eb), top: B:153:0x01be }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03a4 A[Catch: Exception -> 0x0511, TRY_LEAVE, TryCatch #6 {Exception -> 0x0511, blocks: (B:87:0x039e, B:89:0x03a4), top: B:149:0x039e }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:110:0x049c -> B:111:0x04b6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x04e5 -> B:117:0x04ef). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveGetLink(java.lang.String r39, java.lang.String r40, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r41, kotlin.coroutines.Continuation<? super kotlin.Unit> r42) {
        /*
            Method dump skipped, instruction units count: 1414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.resolveGetLink(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$resolveGetLink$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider$resolveGetLink$2", f = "MovieLinkBDProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00042 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fileUrl;
        final /* synthetic */ int $quality;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00042(String str, int i, Continuation<? super C00042> continuation) {
            super(2, continuation);
            this.$fileUrl = str;
            this.$quality = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00042 = new C00042(this.$fileUrl, this.$quality, continuation);
            c00042.L$0 = obj;
            return c00042;
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
                    $this$newExtractorLink.setReferer(this.$fileUrl);
                    $this$newExtractorLink.setQuality(this.$quality);
                    $this$newExtractorLink.setHeaders($this$newExtractorLink.getHeaders());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resolveGetLink$lambda$0$0(SubtitleFile it) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x007f: MOVE (r11 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY] A[D('getWatchUrl' java.lang.String)]), block:B:19:0x007f */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0084: MOVE (r11 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY] A[D('callback' kotlin.jvm.functions.Function1)]), block:B:19:0x007f */
    /* JADX WARN: Not initialized variable reg: 9, insn: 0x0080: MOVE (r10 I:??[OBJECT, ARRAY] A[D('getWatchUrl' java.lang.String)]) = (r9 I:??[OBJECT, ARRAY] A[D('qualityLabel' java.lang.String)]), block:B:19:0x007f */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0109 A[Catch: Exception -> 0x01fb, TryCatch #3 {Exception -> 0x01fb, blocks: (B:62:0x01f5, B:57:0x0197, B:33:0x00f1, B:36:0x0103, B:42:0x0117, B:44:0x011e, B:49:0x0129, B:53:0x0140, B:58:0x019c, B:38:0x0109, B:40:0x0111), top: B:78:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0111 A[Catch: Exception -> 0x01fb, TryCatch #3 {Exception -> 0x01fb, blocks: (B:62:0x01f5, B:57:0x0197, B:33:0x00f1, B:36:0x0103, B:42:0x0117, B:44:0x011e, B:49:0x0129, B:53:0x0140, B:58:0x019c, B:38:0x0109, B:40:0x0111), top: B:78:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0129 A[Catch: Exception -> 0x01fb, TRY_LEAVE, TryCatch #3 {Exception -> 0x01fb, blocks: (B:62:0x01f5, B:57:0x0197, B:33:0x00f1, B:36:0x0103, B:42:0x0117, B:44:0x011e, B:49:0x0129, B:53:0x0140, B:58:0x019c, B:38:0x0109, B:40:0x0111), top: B:78:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveGetWatch(java.lang.String r23, java.lang.String r24, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            Method dump skipped, instruction units count: 544
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieLinkBDProvider.resolveGetWatch(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$resolveGetWatch$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider$resolveGetWatch$2", f = "MovieLinkBDProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00062 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $getWatchUrl;
        final /* synthetic */ int $quality;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00062(String str, int i, Continuation<? super C00062> continuation) {
            super(2, continuation);
            this.$getWatchUrl = str;
            this.$quality = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00062 = new C00062(this.$getWatchUrl, this.$quality, continuation);
            c00062.L$0 = obj;
            return c00062;
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
                    $this$newExtractorLink.setReferer(this.$getWatchUrl);
                    $this$newExtractorLink.setQuality(this.$quality);
                    $this$newExtractorLink.setHeaders($this$newExtractorLink.getHeaders());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieLinkBDProvider$resolveGetWatch$3, reason: invalid class name */
    /* JADX INFO: compiled from: MovieLinkBDProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieLinkBDProvider$resolveGetWatch$3", f = "MovieLinkBDProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $getWatchUrl;
        final /* synthetic */ int $quality;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, int i, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$getWatchUrl = str;
            this.$quality = i;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$getWatchUrl, this.$quality, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
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
                    $this$newExtractorLink.setReferer(this.$getWatchUrl);
                    $this$newExtractorLink.setQuality(this.$quality);
                    $this$newExtractorLink.setHeaders($this$newExtractorLink.getHeaders());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final String extractQualityLabel(String text) {
        if (StringsKt.contains(text, "4K", true) || StringsKt.contains(text, "2160", true)) {
            return "4K";
        }
        if (StringsKt.contains(text, "1080", true)) {
            return "1080p";
        }
        if (StringsKt.contains(text, "720p HEVC", true) || StringsKt.contains(text, "720 HEVC", true)) {
            return "720p HEVC";
        }
        if (StringsKt.contains(text, "720", true)) {
            return "720p";
        }
        if (StringsKt.contains(text, "480", true)) {
            return "480p";
        }
        if (StringsKt.contains(text, "360", true)) {
            return "360p";
        }
        if (StringsKt.contains(text, "Watch Online", true)) {
            return "Stream";
        }
        if (StringsKt.contains(text, "Download", true)) {
            return "Download";
        }
        String string = StringsKt.trim(StringsKt.take(text, 30)).toString();
        if (string.length() == 0) {
            string = "Unknown";
        }
        return string;
    }

    private final int labelToQuality(String label) {
        if (StringsKt.contains(label, "4K", true) || StringsKt.contains(label, "2160", true)) {
            return Qualities.P2160.getValue();
        }
        return StringsKt.contains(label, "1080", true) ? Qualities.P1080.getValue() : StringsKt.contains(label, "720", true) ? Qualities.P720.getValue() : StringsKt.contains(label, "480", true) ? Qualities.P480.getValue() : StringsKt.contains(label, "360", true) ? Qualities.P360.getValue() : Qualities.Unknown.getValue();
    }
}
