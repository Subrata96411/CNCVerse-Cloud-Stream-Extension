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
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
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
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: MoviezwapProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nMoviezwapProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MoviezwapProvider.kt\ncom/cncverse/MoviezwapProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,515:1\n1642#2,10:516\n1915#2:526\n1916#2:528\n1652#2:529\n1696#2,8:530\n1642#2,10:538\n1915#2:548\n1916#2:550\n1652#2:551\n1696#2,8:552\n1642#2,10:561\n1915#2:571\n1916#2:573\n1652#2:574\n1586#2:575\n1661#2,3:576\n1915#2,2:579\n1#3:527\n1#3:549\n1#3:560\n1#3:572\n1#3:581\n*S KotlinDebug\n*F\n+ 1 MoviezwapProvider.kt\ncom/cncverse/MoviezwapProvider\n*L\n72#1:516,10\n72#1:526\n72#1:528\n72#1:529\n74#1:530,8\n117#1:538,10\n117#1:548\n117#1:550\n117#1:551\n119#1:552,8\n157#1:561,10\n157#1:571\n157#1:573\n157#1:574\n222#1:575\n222#1:576,3\n230#1:579,2\n72#1:527\n117#1:549\n157#1:572\n*E\n"})
public final class MoviezwapProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://www.moviezwap.surf";

    @NotNull
    private String name = "Moviezwap";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "te";
    private final boolean hasDownloadSupport = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to(getMainUrl() + "/category/Telugu-(2026)-Movies.html", "Telugu (2026) Movies"), TuplesKt.to(getMainUrl() + "/category/Telugu-(2025)-Movies.html", "Telugu (2025) Movies"), TuplesKt.to(getMainUrl() + "/category/Tamil-(2026)-Movies.html", "Tamil (2026) Movies"), TuplesKt.to(getMainUrl() + "/category/Tamil-(2025)-Movies.html", "Tamil (2025) Movies"), TuplesKt.to(getMainUrl() + "/category/Telugu-Dubbed-Movies-[Hollywood].html", "Telugu Dubbed Hollywood"), TuplesKt.to(getMainUrl() + "/category/HOT-Web-Series.html", "HOT Web Series")});

    /* JADX INFO: renamed from: com.cncverse.MoviezwapProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MoviezwapProvider", f = "MoviezwapProvider.kt", i = {0, 0, 0}, l = {65}, m = "getMainPage", n = {"request", "url", "page"}, nl = {66}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MoviezwapProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MoviezwapProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MoviezwapProvider", f = "MoviezwapProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {125, 177, 184}, m = "load", n = {"url", "url", "document", "title", "poster", "description", "yearText", "year", "seasonLinks", "episodes", "isSeries", "url", "document", "title", "poster", "description", "yearText", "year", "seasonLinks", "isSeries"}, nl = {126, 184, 155}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MoviezwapProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MoviezwapProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MoviezwapProvider", f = "MoviezwapProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {215, 246, 256}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "isCasting", "data", "subtitleCallback", "callback", "document", "downloadLinks", "foundLinks", "$this$forEach$iv", "element$iv", "linkElement", "downloadPageUrl", "linkText", "isCasting", "$i$f$forEach", "$i$a$-forEach-MoviezwapProvider$loadLinks$3", "quality", "data", "subtitleCallback", "callback", "document", "downloadLinks", "foundLinks", "$this$forEach$iv", "element$iv", "linkElement", "downloadPageUrl", "linkText", "actualDownloadUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-MoviezwapProvider$loadLinks$3", "quality"}, nl = {216, 248, 255}, s = {"L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10", "L$11", "Z$0", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10", "L$11", "L$12", "Z$0", "I$0", "I$1", "I$2"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
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

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MoviezwapProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MoviezwapProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MoviezwapProvider", f = "MoviezwapProvider.kt", i = {0, 0, 0}, l = {111}, m = "search", n = {"query", "fixedQuery", "searchUrl"}, nl = {112}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MoviezwapProvider.this.search(null, (Continuation) this);
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

    public boolean getHasDownloadSupport() {
        return this.hasDownloadSupport;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/MoviezwapProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "MoviezwapProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return MoviezwapProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            MoviezwapProvider.context = context;
        }
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r25, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r27) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MoviezwapProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toSearchResult(Element $this$toSearchResult) {
        String href = $this$toSearchResult.attr("href");
        if (!StringsKt.contains$default(href, "/movie/", false, 2, (Object) null)) {
            return null;
        }
        String string = StringsKt.trim($this$toSearchResult.text()).toString();
        if (string.length() == 0) {
            string = StringsKt.replace$default(StringsKt.replace$default(StringsKt.removeSuffix(StringsKt.substringAfterLast$default(href, "/", (String) null, 2, (Object) null), ".html"), "-", " ", false, 4, (Object) null), "(", " (", false, 4, (Object) null);
        }
        String title = string;
        if (StringsKt.isBlank(title)) {
            return null;
        }
        boolean isSeries = new Regex("(?i)(season|episodes?|eps|all episodes|web series)").containsMatchIn(title);
        if (isSeries) {
            return MainAPIKt.newTvSeriesSearchResponse$default(this, title, MainAPIKt.fixUrl(this, href), TvType.TvSeries, false, (Function1) null, 24, (Object) null);
        }
        return MainAPIKt.newMovieSearchResponse$default(this, title, MainAPIKt.fixUrl(this, href), TvType.Movie, false, (Function1) null, 24, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r23, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r24) {
        /*
            Method dump skipped, instruction units count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MoviezwapProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02a3  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r36, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r37) {
        /*
            Method dump skipped, instruction units count: 960
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MoviezwapProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$2$0(String $episodeTitle, int $season, int $epStart, Episode $this$newEpisode) {
        $this$newEpisode.setName($episodeTitle);
        $this$newEpisode.setSeason(Integer.valueOf($season));
        $this$newEpisode.setEpisode(Integer.valueOf($epStart));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.MoviezwapProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MoviezwapProvider$load$2", f = "MoviezwapProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $description;
        final /* synthetic */ String $poster;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Integer num, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$description = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$poster, this.$year, this.$description, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
                    $this$newTvSeriesLoadResponse.setPlot(this.$description);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MoviezwapProvider$load$3, reason: invalid class name */
    /* JADX INFO: compiled from: MoviezwapProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MoviezwapProvider$load$3", f = "MoviezwapProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $description;
        final /* synthetic */ String $poster;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, Integer num, String str2, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$description = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$poster, this.$year, this.$description, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
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
                    $this$newMovieLoadResponse.setPlot(this.$description);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:66|(2:69|(2:72|(2:75|(2:78|(1:80)(1:81))(1:77))(1:74))(1:71))(1:68)|82|125|83|84|129|85|(1:87)(7:88|119|89|(1:93)|94|102|(1:104)(4:105|106|64|(2:107|108)(0)))) */
    /* JADX WARN: Code restructure failed: missing block: B:100:0x0418, code lost:
    
        r33 = r4;
        r13 = r12;
        r4 = r20;
        r19 = r21;
        r21 = r22;
        r22 = r23;
        r20 = r8;
        r23 = r9;
        r15 = r30;
        r14 = r10;
        r9 = 0;
        r10 = r33;
        r12 = r13;
        r8 = r14;
        r11 = r6;
        r18 = r7;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x03f3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x03f4, code lost:
    
        r5 = r15;
        r13 = r12;
        r3 = r17;
        r4 = r20;
        r19 = r21;
        r21 = r22;
        r22 = r23;
        r20 = r26;
        r23 = r27;
        r15 = r30;
        r14 = r10;
        r9 = 0;
        r10 = r33;
        r12 = r13;
        r8 = r14;
        r11 = r18;
        r18 = r19;
        r1 = r41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0417, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Path cross not found for [B:91:0x03d0, B:93:0x03d6], limit reached: 133 */
    /* JADX WARN: Removed duplicated region for block: B:104:0x051a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x023d A[LOOP:0: B:60:0x0237->B:62:0x023d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:105:0x051b -> B:106:0x0538). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r41, boolean r42, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r43, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r44, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r45) {
        /*
            Method dump skipped, instruction units count: 1446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MoviezwapProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
