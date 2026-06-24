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
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.util.List;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: TamilDhoolProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nTamilDhoolProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TamilDhoolProvider.kt\ncom/cncverse/TamilDhoolProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 5 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n*L\n1#1,435:1\n1642#2,10:436\n1915#2:446\n1916#2:448\n1652#2:449\n1642#2,10:450\n1915#2:460\n1916#2:462\n1652#2:463\n1586#2:464\n1661#2,3:465\n777#2:487\n873#2,2:488\n777#2:490\n873#2,2:491\n777#2:493\n873#2,2:494\n1#3:447\n1#3:461\n1#3:469\n1#3:496\n63#4:468\n64#4,15:470\n50#5:485\n43#5:486\n*S KotlinDebug\n*F\n+ 1 TamilDhoolProvider.kt\ncom/cncverse/TamilDhoolProvider\n*L\n72#1:436,10\n72#1:446\n72#1:448\n72#1:449\n94#1:450,10\n94#1:460\n94#1:462\n94#1:463\n109#1:464\n109#1:465,3\n166#1:487\n166#1:488,2\n167#1:490\n167#1:491,2\n168#1:493\n168#1:494,2\n72#1:447\n94#1:461\n164#1:469\n164#1:468\n164#1:470,15\n164#1:485\n164#1:486\n*E\n"})
public final class TamilDhoolProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://www.tamildhool.tech";

    @NotNull
    private String name = "TamilDhool";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";
    private final boolean hasDownloadSupport = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(TvType.TvSeries);

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("zee-tamil", "Zee Tamil TV"), TuplesKt.to("sun-tv", "Sun TV"), TuplesKt.to("vijay-tv", "Vijay TV"), TuplesKt.to("kalaignar-tv", "Kalaignar TV"), TuplesKt.to("news-gossips", "News Gossips TV")});

    /* JADX INFO: renamed from: com.cncverse.TamilDhoolProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.TamilDhoolProvider", f = "TamilDhoolProvider.kt", i = {0, 0, 0}, l = {67}, m = "getMainPage", n = {"request", "query", "page"}, nl = {71}, s = {"L$0", "L$1", "I$0"}, v = 2)
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
            return TamilDhoolProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.TamilDhoolProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.TamilDhoolProvider", f = "TamilDhoolProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {101, 136}, m = "load", n = {"url", "url", "doc", "title", "posterRegex", "posterRaw", "poster", "linkElements", "link", "episodes"}, nl = {102, -1}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
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
        int label;
        /* synthetic */ Object result;

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TamilDhoolProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.TamilDhoolProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.TamilDhoolProvider", f = "TamilDhoolProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {170}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "link", "thiraione", "dailymotion", "youtube", "isCasting"}, nl = {189}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
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

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TamilDhoolProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.TamilDhoolProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.TamilDhoolProvider", f = "TamilDhoolProvider.kt", i = {0, 0}, l = {93}, m = "search", n = {"query", "encodedQuery"}, nl = {94}, s = {"L$0", "L$1"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TamilDhoolProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/TamilDhoolProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "TamilDhoolProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return TamilDhoolProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            TamilDhoolProvider.context = context;
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

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cncverse/TamilDhoolProvider$TamilDhoolLinks;", "", "sourceName", "", "sourceLink", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getSourceName", "()Ljava/lang/String;", "getSourceLink", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "TamilDhoolProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TamilDhoolLinks {

        @JsonProperty("sourceLink")
        @NotNull
        private final String sourceLink;

        @JsonProperty("sourceName")
        @NotNull
        private final String sourceName;

        public static /* synthetic */ TamilDhoolLinks copy$default(TamilDhoolLinks tamilDhoolLinks, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tamilDhoolLinks.sourceName;
            }
            if ((i & 2) != 0) {
                str2 = tamilDhoolLinks.sourceLink;
            }
            return tamilDhoolLinks.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSourceName() {
            return this.sourceName;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSourceLink() {
            return this.sourceLink;
        }

        @NotNull
        public final TamilDhoolLinks copy(@JsonProperty("sourceName") @NotNull String sourceName, @JsonProperty("sourceLink") @NotNull String sourceLink) {
            return new TamilDhoolLinks(sourceName, sourceLink);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TamilDhoolLinks)) {
                return false;
            }
            TamilDhoolLinks tamilDhoolLinks = (TamilDhoolLinks) other;
            return Intrinsics.areEqual(this.sourceName, tamilDhoolLinks.sourceName) && Intrinsics.areEqual(this.sourceLink, tamilDhoolLinks.sourceLink);
        }

        public int hashCode() {
            return (this.sourceName.hashCode() * 31) + this.sourceLink.hashCode();
        }

        @NotNull
        public String toString() {
            return "TamilDhoolLinks(sourceName=" + this.sourceName + ", sourceLink=" + this.sourceLink + ')';
        }

        public TamilDhoolLinks(@JsonProperty("sourceName") @NotNull String sourceName, @JsonProperty("sourceLink") @NotNull String sourceLink) {
            this.sourceName = sourceName;
            this.sourceLink = sourceLink;
        }

        @NotNull
        public final String getSourceName() {
            return this.sourceName;
        }

        @NotNull
        public final String getSourceLink() {
            return this.sourceLink;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r29, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r30, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r31) {
        /*
            Method dump skipped, instruction units count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.TamilDhoolProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toSearchResult(Element $this$toSearchResult) {
        String strText;
        String title;
        final String posterUrl;
        Element elementSelectFirst = $this$toSearchResult.selectFirst("section.entry-body > h3 > a");
        if (elementSelectFirst == null || (strText = elementSelectFirst.text()) == null || (title = StringsKt.trim(strText).toString()) == null) {
            return null;
        }
        TamilDhoolProvider tamilDhoolProvider = this;
        Element elementSelectFirst2 = $this$toSearchResult.selectFirst("section.entry-body > h3 > a");
        String href = MainAPIKt.fixUrl(tamilDhoolProvider, String.valueOf(elementSelectFirst2 != null ? elementSelectFirst2.attr("href") : null));
        Element elementSelectFirst3 = $this$toSearchResult.selectFirst("div.post-thumb > a > picture > img");
        if (elementSelectFirst3 == null || (posterUrl = elementSelectFirst3.attr("src")) == null) {
            TamilDhoolProvider tamilDhoolProvider2 = this;
            Element elementSelectFirst4 = $this$toSearchResult.selectFirst("div.post-thumb > a > img");
            posterUrl = MainAPIKt.fixUrlNull(tamilDhoolProvider2, elementSelectFirst4 != null ? elementSelectFirst4.attr("src") : null);
        }
        return MainAPIKt.newTvSeriesSearchResponse$default(this, title, href, TvType.TvSeries, false, new Function1() { // from class: com.cncverse.TamilDhoolProvider$$ExternalSyntheticLambda1
            public final Object invoke(Object obj) {
                return TamilDhoolProvider.toSearchResult$lambda$0(posterUrl, this, (TvSeriesSearchResponse) obj);
            }
        }, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResult$lambda$0(String $posterUrl, TamilDhoolProvider this$0, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl($posterUrl);
        $this$newTvSeriesSearchResponse.setPosterHeaders(MapsKt.mapOf(TuplesKt.to("referer", this$0.getMainUrl() + '/')));
        $this$newTvSeriesSearchResponse.setQuality(SearchQuality.HD);
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
            Method dump skipped, instruction units count: 298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.TamilDhoolProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r28, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r29) {
        /*
            Method dump skipped, instruction units count: 582
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.TamilDhoolProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$1(String $title, String $poster, Episode $this$newEpisode) {
        $this$newEpisode.setName($title);
        $this$newEpisode.setSeason(1);
        $this$newEpisode.setEpisode(1);
        $this$newEpisode.setPosterUrl($poster);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.cncverse.TamilDhoolProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.TamilDhoolProvider$load$2", f = "TamilDhoolProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $poster;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ TamilDhoolProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, TamilDhoolProvider tamilDhoolProvider, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.this$0 = tamilDhoolProvider;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$poster, this.this$0, continuation);
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
                    String str = this.$poster;
                    $this$newTvSeriesLoadResponse.setPosterUrl(str != null ? StringsKt.trim(str).toString() : null);
                    $this$newTvSeriesLoadResponse.setPosterHeaders(MapsKt.mapOf(TuplesKt.to("referer", this.this$0.getMainUrl() + '/')));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x028a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x028b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r22, boolean r23, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r24, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r25, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r26) {
        /*
            Method dump skipped, instruction units count: 674
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.TamilDhoolProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.cncverse.TamilDhoolProvider$loadLinks$3, reason: invalid class name */
    /* JADX INFO: compiled from: TamilDhoolProvider.kt */
    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.TamilDhoolProvider$loadLinks$3", f = "TamilDhoolProvider.kt", i = {}, l = {173, 182, 185}, m = "invokeSuspend", n = {}, nl = {172, 184, 188}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<ExtractorLink, Unit> $callback;
        final /* synthetic */ List<TamilDhoolLinks> $dailymotion;
        final /* synthetic */ Function1<SubtitleFile, Unit> $subtitleCallback;
        final /* synthetic */ List<TamilDhoolLinks> $thiraione;
        final /* synthetic */ List<TamilDhoolLinks> $youtube;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(List<TamilDhoolLinks> list, Function1<? super ExtractorLink, Unit> function1, List<TamilDhoolLinks> list2, Function1<? super SubtitleFile, Unit> function12, List<TamilDhoolLinks> list3, Continuation<? super AnonymousClass3> continuation) {
            super(1, continuation);
            this.$thiraione = list;
            this.$callback = function1;
            this.$dailymotion = list2;
            this.$subtitleCallback = function12;
            this.$youtube = list3;
        }

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass3(this.$thiraione, this.$callback, this.$dailymotion, this.$subtitleCallback, this.$youtube, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return create(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x00d8  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0112  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r26) {
            /*
                Method dump skipped, instruction units count: 332
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cncverse.TamilDhoolProvider.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence invokeSuspend$lambda$0(TamilDhoolLinks it) {
            return it.getSourceName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence invokeSuspend$lambda$1(TamilDhoolLinks it) {
            return it.getSourceName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence invokeSuspend$lambda$2(TamilDhoolLinks it) {
            return it.getSourceLink();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence invokeSuspend$lambda$3(TamilDhoolLinks it) {
            return it.getSourceLink();
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
