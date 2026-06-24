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
import com.lagradost.cloudstream3.AnimeSearchResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.List;
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
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: RtallyProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nRtallyProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RtallyProvider.kt\ncom/cncverse/RtallyProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,526:1\n1642#2,10:527\n1915#2:537\n1916#2:539\n1652#2:540\n1642#2,10:541\n1915#2:551\n1916#2:553\n1652#2:554\n1642#2,10:555\n1915#2:565\n1916#2:567\n1652#2:568\n1915#2:569\n1924#2,3:570\n1924#2,3:573\n1924#2,3:576\n1916#2:579\n1924#2,3:580\n1915#2,2:583\n1915#2,2:585\n1#3:538\n1#3:552\n1#3:566\n1#3:587\n*S KotlinDebug\n*F\n+ 1 RtallyProvider.kt\ncom/cncverse/RtallyProvider\n*L\n89#1:527,10\n89#1:537\n89#1:539\n89#1:540\n129#1:541,10\n129#1:551\n129#1:553\n129#1:554\n148#1:555,10\n148#1:565\n148#1:567\n148#1:568\n160#1:569\n165#1:570,3\n174#1:573,3\n183#1:576,3\n160#1:579\n192#1:580,3\n206#1:583,2\n273#1:585,2\n89#1:538\n129#1:552\n148#1:566\n*E\n"})
public final class RtallyProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;
    private final boolean hasQuickSearch;

    @NotNull
    private String mainUrl = "https://www.rtally.shop";

    @NotNull
    private String name = "Rtally";

    @NotNull
    private String lang = "ta";
    private final boolean hasMainPage = true;
    private final boolean hasDownloadSupport = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries, TvType.AsianDrama, TvType.AnimeMovie, TvType.Anime});

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("/categories/trending", "Trending"), TuplesKt.to("/categories/featured", "Featured"), TuplesKt.to("/categories/hollywood", "Hollywood"), TuplesKt.to("/categories/bengali", "Bangla"), TuplesKt.to("/categories/bollywood", "Bollywood"), TuplesKt.to("/categories/tv-shows", "Tv Shows"), TuplesKt.to("/categories/korean", "Korean"), TuplesKt.to("/categories/anime", "Anime")});

    @NotNull
    private final Map<String, String> headers = MapsKt.mapOf(TuplesKt.to("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36"));

    @NotNull
    private final Regex fileMoonRegex = new Regex("\"multiLinksDl\":\\s*\"([^\"]+)\"");

    @NotNull
    private final Regex streamwishMultiUrlRegex = new Regex("\"streamwishMultiUrl\":\\s*\"([^\"]+)\"");

    @NotNull
    private final Regex vidhideplusRegex = new Regex("\"multiLinksSl\":\\s*\"([^\"]+)\"");

    /* JADX INFO: renamed from: com.cncverse.RtallyProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.RtallyProvider", f = "RtallyProvider.kt", i = {0, 0}, l = {84}, m = "getMainPage", n = {"request", "page"}, nl = {88}, s = {"L$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RtallyProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.RtallyProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.RtallyProvider", f = "RtallyProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {134, 197, 209}, m = "load", n = {"url", "url", "doc", "title", "image", "plot", "year", "recommendations", "episode", "episodesData", "scriptHtml", "linkList", "url", "doc", "title", "image", "plot", "year", "recommendations", "episode", "links"}, nl = {138, 205, -1}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
    static final class C00001 extends ContinuationImpl {
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
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RtallyProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.RtallyProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.RtallyProvider", f = "RtallyProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {274}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "$this$forEach$iv", "element$iv", "it", "isCasting", "$i$f$forEach", "$i$a$-forEach-RtallyProvider$loadLinks$3"}, nl = {279}, s = {"L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "Z$0", "I$0", "I$1"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        int I$1;
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

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RtallyProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.RtallyProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.RtallyProvider", f = "RtallyProvider.kt", i = {0}, l = {124}, m = "search", n = {"query"}, nl = {128}, s = {"L$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RtallyProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/RtallyProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "Rtally_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return RtallyProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            RtallyProvider.context = context;
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
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r25, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r27) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.RtallyProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toResult(Element post) {
        String title = post.select("h4").text();
        String url = getMainUrl() + post.attr("href");
        final Ref.ObjectRef posterUrl = new Ref.ObjectRef();
        posterUrl.element = post.select("img").attr("src");
        CharSequence charSequence = (CharSequence) posterUrl.element;
        if (charSequence == null || charSequence.length() == 0) {
            String styleAttr = post.select("div[style*=background-image]").attr("style");
            posterUrl.element = StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringAfter$default(styleAttr, "url(", (String) null, 2, (Object) null), ")", (String) null, 2, (Object) null), "?", (String) null, 2, (Object) null);
        }
        final String language = post.select("div.absolute.bottom-2.left-2").text();
        post.select("div.absolute.bottom-2.right-2").text();
        post.select("h5.border").text();
        return MainAPIKt.newAnimeSearchResponse$default(this, title, url, TvType.Movie, false, new Function1() { // from class: com.cncverse.RtallyProvider$$ExternalSyntheticLambda10
            public final Object invoke(Object obj) {
                return RtallyProvider.toResult$lambda$0(posterUrl, language, (AnimeSearchResponse) obj);
            }
        }, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toResult$lambda$0(Ref.ObjectRef $posterUrl, String $language, AnimeSearchResponse $this$newAnimeSearchResponse) {
        $this$newAnimeSearchResponse.setPosterUrl((String) $posterUrl.element);
        boolean z = StringsKt.contains$default($language, "Dual", false, 2, (Object) null) || StringsKt.contains$default($language, "Hindi", false, 2, (Object) null) || StringsKt.contains$default($language, "Tamil", false, 2, (Object) null) || StringsKt.contains$default($language, "Telugu", false, 2, (Object) null) || StringsKt.contains$default($language, "Bangla", false, 2, (Object) null);
        MainAPIKt.addDubStatus$default($this$newAnimeSearchResponse, z, StringsKt.contains$default($language, "Eng-Sub", false, 2, (Object) null), (Integer) null, (Integer) null, 12, (Object) null);
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
            Method dump skipped, instruction units count: 244
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.RtallyProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02ec  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r43, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r44) {
        /*
            Method dump skipped, instruction units count: 1594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.RtallyProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$0$0(Element $it, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($it.select("img").attr("src"));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence load$lambda$1(Element it) {
        return it.html();
    }

    /* JADX INFO: renamed from: com.cncverse.RtallyProvider$load$4, reason: invalid class name */
    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.RtallyProvider$load$4", f = "RtallyProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $image;
        final /* synthetic */ String $plot;
        final /* synthetic */ List<MovieSearchResponse> $recommendations;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<String> objectRef, String str, Integer num, List<MovieSearchResponse> list, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$image = objectRef;
            this.$plot = str;
            this.$year = num;
            this.$recommendations = list;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$image, this.$plot, this.$year, this.$recommendations, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
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
                    $this$newTvSeriesLoadResponse.setPosterUrl((String) this.$image.element);
                    $this$newTvSeriesLoadResponse.setPlot(this.$plot);
                    $this$newTvSeriesLoadResponse.setYear(this.$year);
                    $this$newTvSeriesLoadResponse.setRecommendations(this.$recommendations);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.RtallyProvider$load$6, reason: invalid class name */
    /* JADX INFO: compiled from: RtallyProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.RtallyProvider$load$6", f = "RtallyProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $image;
        final /* synthetic */ String $plot;
        final /* synthetic */ List<MovieSearchResponse> $recommendations;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(Ref.ObjectRef<String> objectRef, String str, Integer num, List<MovieSearchResponse> list, Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
            this.$image = objectRef;
            this.$plot = str;
            this.$year = num;
            this.$recommendations = list;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass6 = new AnonymousClass6(this.$image, this.$plot, this.$year, this.$recommendations, continuation);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
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
                    $this$newMovieLoadResponse.setPosterUrl((String) this.$image.element);
                    $this$newMovieLoadResponse.setPlot(this.$plot);
                    $this$newMovieLoadResponse.setYear(this.$year);
                    $this$newMovieLoadResponse.setRecommendations(this.$recommendations);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final String downloadToEmbedUrl(Element urlElement) {
        String url = urlElement.attr("href");
        return StringsKt.contains$default(url, "filemoon", false, 2, (Object) null) ? StringsKt.replace$default(url, "/download/", "/e/", false, 4, (Object) null) + " ; " : StringsKt.contains$default(url, "vidhideplus", false, 2, (Object) null) ? StringsKt.replace$default(url, "/download/", "/v/", false, 4, (Object) null) + " ; " : StringsKt.contains$default(url, "vidhidepre", false, 2, (Object) null) ? StringsKt.replace$default(url, "/d/", "/v/", false, 4, (Object) null) + " ; " : StringsKt.contains$default(url, "playerwish", false, 2, (Object) null) ? StringsKt.replace$default(url, "/d/", "/e/", false, 4, (Object) null) + " ; " : url + " ; ";
    }

    private final String extractFileMoonUrls(String text) {
        List groupValues;
        MatchResult fileMoonMatch = Regex.find$default(this.fileMoonRegex, text, 0, 2, (Object) null);
        if (fileMoonMatch == null || (groupValues = fileMoonMatch.getGroupValues()) == null) {
            return null;
        }
        return (String) CollectionsKt.getOrNull(groupValues, 1);
    }

    private final String extractStreamwishUrls(String text) {
        List groupValues;
        MatchResult streamwishMultiUrlMatch = Regex.find$default(this.streamwishMultiUrlRegex, text, 0, 2, (Object) null);
        if (streamwishMultiUrlMatch == null || (groupValues = streamwishMultiUrlMatch.getGroupValues()) == null) {
            return null;
        }
        return (String) CollectionsKt.getOrNull(groupValues, 1);
    }

    private final String extractVidhideplus(String text) {
        List groupValues;
        MatchResult vidhideplusMatch = Regex.find$default(this.vidhideplusRegex, text, 0, 2, (Object) null);
        if (vidhideplusMatch == null || (groupValues = vidhideplusMatch.getGroupValues()) == null) {
            return null;
        }
        return (String) CollectionsKt.getOrNull(groupValues, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x014a -> B:47:0x0152). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r26, boolean r27, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r28, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r29, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r30) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.RtallyProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
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
