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
import com.lagradost.cloudstream3.MovieSearchResponse;
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
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: Watch32Provider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nWatch32Provider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Watch32Provider.kt\ncom/cncverse/Watch32Provider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,496:1\n1642#2,10:497\n1915#2:507\n1916#2:509\n1652#2:510\n1642#2,10:511\n1915#2:521\n1916#2:523\n1652#2:524\n1586#2:525\n1661#2,3:526\n1586#2:529\n1661#2,3:530\n1586#2:533\n1661#2,3:534\n1#3:508\n1#3:522\n1#3:537\n*S KotlinDebug\n*F\n+ 1 Watch32Provider.kt\ncom/cncverse/Watch32Provider\n*L\n73#1:497,10\n73#1:507\n73#1:509\n73#1:510\n117#1:511,10\n117#1:521\n117#1:523\n117#1:524\n137#1:525\n137#1:526,3\n147#1:529\n147#1:530,3\n173#1:533\n173#1:534,3\n73#1:508\n117#1:522\n*E\n"})
public final class Watch32Provider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://watch32.sx";

    @NotNull
    private String name = "Watch32";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    @NotNull
    private String lang = "en";
    private final boolean hasMainPage = true;
    private final boolean hasQuickSearch = true;

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("movie", "Popular Movies"), TuplesKt.to("tv-show", "Popular TV Shows"), TuplesKt.to("genre/animation", "Animations"), TuplesKt.to("country/IN", "India"), TuplesKt.to("country/FR", "France")});

    /* JADX INFO: renamed from: com.cncverse.Watch32Provider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Watch32Provider", f = "Watch32Provider.kt", i = {0, 0}, l = {72}, m = "getMainPage", n = {"request", "page"}, nl = {73}, s = {"L$0", "I$0"}, v = 2)
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
            return Watch32Provider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.Watch32Provider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Watch32Provider", f = "Watch32Provider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, l = {125, 162, 167, 170, 190, 200}, m = "load", n = {"url", "url", "doc", "title", "image", "regex", "matchResult", "coverImage", "synopsis", "rowLines", "releasedYear", "genres", "duration", "type", "movieUrlData", "episodes", "url", "doc", "title", "image", "regex", "matchResult", "coverImage", "synopsis", "rowLines", "releasedYear", "genres", "duration", "type", "movieUrlData", "episodes", "web", "dataId", "url", "doc", "title", "image", "regex", "matchResult", "coverImage", "synopsis", "rowLines", "releasedYear", "genres", "duration", "type", "movieUrlData", "episodes", "web", "dataId", "season", "seasonId", "numSeason", "url", "doc", "title", "image", "regex", "matchResult", "coverImage", "synopsis", "rowLines", "releasedYear", "genres", "duration", "type", "movieUrlData", "episodes", "web", "dataId", "url", "doc", "title", "image", "regex", "matchResult", "coverImage", "synopsis", "rowLines", "releasedYear", "genres", "duration", "type", "movieUrlData", "episodes", "web", "dataId"}, nl = {126, 163, 168, 172, 200, 189}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
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
            return Watch32Provider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.Watch32Provider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Watch32Provider", f = "Watch32Provider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {238, 244, 246}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "isCasting", "data", "subtitleCallback", "callback", "web", "vidDataIds", "vidDataId", "vidId", "isCasting", "data", "subtitleCallback", "callback", "web", "vidDataIds", "vidDataId", "vidId", "www", "link", "isCasting"}, nl = {239, 245, 249}, s = {"L$0", "L$1", "L$2", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6", "L$7", "L$8", "L$9", "Z$0"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        Object L$0;
        Object L$1;
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
            return Watch32Provider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.Watch32Provider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Watch32Provider", f = "Watch32Provider.kt", i = {0}, l = {109}, m = "search", n = {"query"}, nl = {115}, s = {"L$0"}, v = 2)
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
            return Watch32Provider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/Watch32Provider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "Watch32_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return Watch32Provider.context;
        }

        public final void setContext(@Nullable Context context) {
            Watch32Provider.context = context;
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
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
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

    public boolean getHasQuickSearch() {
        return this.hasQuickSearch;
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
            Method dump skipped, instruction units count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.Watch32Provider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toResult(final Element post) {
        String strAttr;
        Element elementSelectFirst = post.selectFirst("a");
        if (elementSelectFirst == null || (strAttr = elementSelectFirst.attr("title")) == null) {
            strAttr = "";
        }
        String title = strAttr;
        StringBuilder sbAppend = new StringBuilder().append(getMainUrl()).append('/');
        Element elementSelectFirst2 = post.selectFirst("a");
        String url = sbAppend.append(elementSelectFirst2 != null ? elementSelectFirst2.attr("href") : null).toString();
        return MainAPIKt.newMovieSearchResponse$default(this, title, url, TvType.Movie, false, new Function1() { // from class: com.cncverse.Watch32Provider$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return Watch32Provider.toResult$lambda$0(post, (MovieSearchResponse) obj);
            }
        }, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toResult$lambda$0(Element $post, MovieSearchResponse $this$newMovieSearchResponse) {
        Element elementSelectFirst = $post.selectFirst("img");
        $this$newMovieSearchResponse.setPosterUrl(elementSelectFirst != null ? elementSelectFirst.attr("data-src") : null);
        return Unit.INSTANCE;
    }

    private final SearchResponse toSearchResult(final Element post) {
        String strText;
        Element elementSelectFirst = post.selectFirst("h3");
        if (elementSelectFirst == null || (strText = elementSelectFirst.text()) == null) {
            strText = "";
        }
        String title = strText;
        StringBuilder sbAppend = new StringBuilder().append(getMainUrl()).append('/');
        Element elementSelectFirst2 = post.selectFirst("a");
        String url = sbAppend.append(elementSelectFirst2 != null ? elementSelectFirst2.attr("href") : null).toString();
        return MainAPIKt.newMovieSearchResponse$default(this, title, url, TvType.Movie, false, new Function1() { // from class: com.cncverse.Watch32Provider$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return Watch32Provider.toSearchResult$lambda$0(post, (MovieSearchResponse) obj);
            }
        }, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResult$lambda$0(Element $post, MovieSearchResponse $this$newMovieSearchResponse) {
        Element elementSelectFirst = $post.selectFirst("img");
        $this$newMovieSearchResponse.setPosterUrl(elementSelectFirst != null ? elementSelectFirst.attr("src") : null);
        return Unit.INSTANCE;
    }

    @Nullable
    public Object quickSearch(@NotNull String query, @NotNull Continuation<? super List<? extends SearchResponse>> continuation) {
        return search(query, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r27, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r28) {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.Watch32Provider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Path cross not found for [B:44:0x0337, B:46:0x033d], limit reached: 134 */
    /* JADX WARN: Path cross not found for [B:53:0x038b, B:58:0x03a6], limit reached: 134 */
    /* JADX WARN: Path cross not found for [B:61:0x03b5, B:70:0x0415], limit reached: 134 */
    /* JADX WARN: Path cross not found for [B:75:0x042b, B:82:0x0454], limit reached: 134 */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0611  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0717 A[LOOP:0: B:109:0x0711->B:111:0x0717, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x07aa  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x07cc  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0826  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x08d1  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0364 A[LOOP:1: B:48:0x035e->B:50:0x0364, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x04f2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0528  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x06c4 -> B:108:0x06db). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r58, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r59) throws kotlin.NotImplementedError {
        /*
            Method dump skipped, instruction units count: 2482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.Watch32Provider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$2$0(Element $it, int $numSeason, Ref.IntRef $numEpi, Ref.ObjectRef $coverImage, Episode $this$newEpisode) {
        $this$newEpisode.setName((String) StringsKt.split$default($it.text(), new String[]{":"}, false, 0, 6, (Object) null).get(1));
        $this$newEpisode.setSeason(Integer.valueOf($numSeason + 1));
        $numEpi.element++;
        $this$newEpisode.setEpisode(Integer.valueOf($numEpi.element));
        $this$newEpisode.setPosterUrl((String) $coverImage.element);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.Watch32Provider$load$3, reason: invalid class name */
    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Watch32Provider$load$3", f = "Watch32Provider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $coverImage;
        final /* synthetic */ String $duration;
        final /* synthetic */ List<String> $genres;
        final /* synthetic */ String $image;
        final /* synthetic */ String $releasedYear;
        final /* synthetic */ String $synopsis;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.ObjectRef<String> objectRef, String str, String str2, List<String> list, String str3, String str4, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$coverImage = objectRef;
            this.$image = str;
            this.$synopsis = str2;
            this.$genres = list;
            this.$releasedYear = str3;
            this.$duration = str4;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$coverImage, this.$image, this.$synopsis, this.$genres, this.$releasedYear, this.$duration, continuation);
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
                    $this$newMovieLoadResponse.setBackgroundPosterUrl((String) this.$coverImage.element);
                    $this$newMovieLoadResponse.setPosterUrl(this.$image);
                    $this$newMovieLoadResponse.setPlot(this.$synopsis);
                    $this$newMovieLoadResponse.setTags(this.$genres);
                    String str = this.$releasedYear;
                    $this$newMovieLoadResponse.setYear(str != null ? StringsKt.toIntOrNull(str) : null);
                    String str2 = this.$duration;
                    $this$newMovieLoadResponse.setDuration(str2 != null ? StringsKt.toIntOrNull(str2) : null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.Watch32Provider$load$4, reason: invalid class name */
    /* JADX INFO: compiled from: Watch32Provider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.Watch32Provider$load$4", f = "Watch32Provider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $coverImage;
        final /* synthetic */ String $duration;
        final /* synthetic */ List<String> $genres;
        final /* synthetic */ String $image;
        final /* synthetic */ String $releasedYear;
        final /* synthetic */ String $synopsis;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<String> objectRef, String str, String str2, List<String> list, String str3, String str4, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$coverImage = objectRef;
            this.$image = str;
            this.$synopsis = str2;
            this.$genres = list;
            this.$releasedYear = str3;
            this.$duration = str4;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$coverImage, this.$image, this.$synopsis, this.$genres, this.$releasedYear, this.$duration, continuation);
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
                    $this$newTvSeriesLoadResponse.setBackgroundPosterUrl((String) this.$coverImage.element);
                    $this$newTvSeriesLoadResponse.setPosterUrl(this.$image);
                    $this$newTvSeriesLoadResponse.setPlot(this.$synopsis);
                    $this$newTvSeriesLoadResponse.setTags(this.$genres);
                    String str = this.$releasedYear;
                    $this$newTvSeriesLoadResponse.setYear(str != null ? StringsKt.toIntOrNull(str) : null);
                    String str2 = this.$duration;
                    $this$newTvSeriesLoadResponse.setDuration(str2 != null ? StringsKt.toIntOrNull(str2) : null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x02af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x02b0 -> B:58:0x02ba). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r33, boolean r34, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r35, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r36, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r37) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 718
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.Watch32Provider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
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
