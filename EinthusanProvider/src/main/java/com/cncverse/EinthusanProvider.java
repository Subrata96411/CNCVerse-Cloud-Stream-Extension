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
import com.lagradost.cloudstream3.ActorData;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
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

/* JADX INFO: compiled from: EinthusanProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nEinthusanProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EinthusanProvider.kt\ncom/cncverse/EinthusanProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,465:1\n1642#2,10:466\n1915#2:476\n1916#2:478\n1652#2:479\n1642#2,10:480\n1915#2:490\n1916#2:492\n1652#2:493\n1642#2,10:494\n1915#2:504\n1916#2:506\n1652#2:507\n1642#2,10:508\n1915#2:518\n1916#2:520\n1652#2:521\n1642#2,10:522\n1915#2:532\n1916#2:534\n1652#2:535\n1642#2,10:536\n1915#2:546\n1916#2:548\n1652#2:549\n1642#2,10:550\n1915#2:560\n1916#2:562\n1652#2:563\n1642#2,10:564\n1915#2:574\n1916#2:576\n1652#2:577\n1642#2,10:578\n1915#2:588\n1916#2:590\n1652#2:591\n1068#2:592\n1586#2:594\n1661#2,3:595\n1586#2:598\n1661#2,3:599\n1#3:477\n1#3:491\n1#3:505\n1#3:519\n1#3:533\n1#3:547\n1#3:561\n1#3:575\n1#3:589\n1#3:593\n*S KotlinDebug\n*F\n+ 1 EinthusanProvider.kt\ncom/cncverse/EinthusanProvider\n*L\n68#1:466,10\n68#1:476\n68#1:478\n68#1:479\n91#1:480,10\n91#1:490\n91#1:492\n91#1:493\n94#1:494,10\n94#1:504\n94#1:506\n94#1:507\n97#1:508,10\n97#1:518\n97#1:520\n97#1:521\n100#1:522,10\n100#1:532\n100#1:534\n100#1:535\n103#1:536,10\n103#1:546\n103#1:548\n103#1:549\n106#1:550,10\n106#1:560\n106#1:562\n106#1:563\n109#1:564,10\n109#1:574\n109#1:576\n109#1:577\n112#1:578,10\n112#1:588\n112#1:590\n112#1:591\n117#1:592\n138#1:594\n138#1:595,3\n146#1:598\n146#1:599,3\n68#1:477\n91#1:491\n94#1:505\n97#1:519\n100#1:533\n103#1:547\n106#1:561\n109#1:575\n112#1:589\n*E\n"})
public final class EinthusanProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://einthusan.tv";

    @NotNull
    private String name = "Einthusan";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";
    private final boolean hasDownloadSupport = true;
    private boolean sequentialMainPage = true;
    private long sequentialMainPageDelay = 100;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(TvType.Movie);

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=tamil", "Tamil Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=hindi", "Hindi Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=telugu", "Telugu Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=malayalam", "Malayalam Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=kannada", "Kannada Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=bengali", "Bengali Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=marathi", "Marathi Movies"), TuplesKt.to(getMainUrl() + "/movie/results/?find=Recent&lang=punjabi", "Punjabi Movies")});

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider", f = "EinthusanProvider.kt", i = {0, 0, 1, 1}, l = {62, 64}, m = "getMainPage", n = {"request", "page", "request", "page"}, nl = {64, 61}, s = {"L$0", "I$0", "L$0", "I$0"}, v = 2)
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
            return EinthusanProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider", f = "EinthusanProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {130, 158}, m = "load", n = {"url", "url", "doc", "title", "href", "poster", "tags", "year", "description", "score", "actors", "mp4link", "m3u8link"}, nl = {132, -1}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11"}, v = 2)
    static final class C00001 extends ContinuationImpl {
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

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EinthusanProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider", f = "EinthusanProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {197, 208}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "mp4link", "m3u8link", "ipfind", "fixedmp4link", "fixedm3u8link", "isCasting", "data", "subtitleCallback", "callback", "mp4link", "m3u8link", "ipfind", "fixedmp4link", "fixedm3u8link", "isCasting"}, nl = {196, 207}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0"}, v = 2)
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
            return EinthusanProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider", f = "EinthusanProvider.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7}, l = {91, 94, 97, 100, 103, 106, 109, 112}, m = "search", n = {"query", "fixedQuery", "query", "fixedQuery", "resultTamil", "query", "fixedQuery", "resultTamil", "resultHindi", "query", "fixedQuery", "resultTamil", "resultHindi", "resultMalayalam", "query", "fixedQuery", "resultTamil", "resultHindi", "resultMalayalam", "resultTelugu", "query", "fixedQuery", "resultTamil", "resultHindi", "resultMalayalam", "resultTelugu", "resultKannada", "query", "fixedQuery", "resultTamil", "resultHindi", "resultMalayalam", "resultTelugu", "resultKannada", "resultBengali", "query", "fixedQuery", "resultTamil", "resultHindi", "resultMalayalam", "resultTelugu", "resultKannada", "resultBengali", "resultMarathi"}, nl = {466, 480, 494, 508, 522, 536, 550, 564}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
    static final class C00021 extends ContinuationImpl {
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

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return EinthusanProvider.this.search(null, (Continuation) this);
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

    public boolean getSequentialMainPage() {
        return this.sequentialMainPage;
    }

    public void setSequentialMainPage(boolean z) {
        this.sequentialMainPage = z;
    }

    public long getSequentialMainPageDelay() {
        return this.sequentialMainPageDelay;
    }

    public void setSequentialMainPageDelay(long j) {
        this.sequentialMainPageDelay = j;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/EinthusanProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "EinthusanProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return EinthusanProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            EinthusanProvider.context = context;
        }
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r25, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r27) {
        /*
            Method dump skipped, instruction units count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.EinthusanProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toSearchResult(Element $this$toSearchResult) {
        String strText;
        String title;
        Element elementSelectFirst = $this$toSearchResult.selectFirst("div.block2 > a.title > h3");
        if (elementSelectFirst == null || (strText = elementSelectFirst.text()) == null || (title = StringsKt.trim(strText).toString()) == null) {
            return null;
        }
        EinthusanProvider einthusanProvider = this;
        StringBuilder sbAppend = new StringBuilder().append(getMainUrl());
        Element elementSelectFirst2 = $this$toSearchResult.selectFirst("div.block2 > a.title");
        String href = MainAPIKt.fixUrl(einthusanProvider, sbAppend.append(elementSelectFirst2 != null ? elementSelectFirst2.attr("href") : null).toString());
        EinthusanProvider einthusanProvider2 = this;
        StringBuilder sbAppend2 = new StringBuilder().append("https:");
        Element elementSelectFirst3 = $this$toSearchResult.selectFirst("div.block1 > a > img");
        final String posterUrl = MainAPIKt.fixUrlNull(einthusanProvider2, sbAppend2.append(elementSelectFirst3 != null ? elementSelectFirst3.attr("src") : null).toString());
        return MainAPIKt.newMovieSearchResponse$default(this, title, href, TvType.Movie, false, new Function1() { // from class: com.cncverse.EinthusanProvider$$ExternalSyntheticLambda8
            public final Object invoke(Object obj) {
                return EinthusanProvider.toSearchResult$lambda$0(posterUrl, (MovieSearchResponse) obj);
            }
        }, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResult$lambda$0(String $posterUrl, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($posterUrl);
        $this$newMovieSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x06ba  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x06ef  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x026c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0315 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x03c3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0476 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x04a6  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0530 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0563  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x05f0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x05f1  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x06b9 A[RETURN] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r32) {
        /*
            Method dump skipped, instruction units count: 1916
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.EinthusanProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r34, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r35) {
        /*
            Method dump skipped, instruction units count: 808
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.EinthusanProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider$load$2", f = "EinthusanProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<ActorData> $actors;
        final /* synthetic */ String $description;
        final /* synthetic */ String $poster;
        final /* synthetic */ Score $score;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Integer num, String str2, List<String> list, Score score, List<ActorData> list2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$description = str2;
            this.$tags = list;
            this.$score = score;
            this.$actors = list2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$poster, this.$year, this.$description, this.$tags, this.$score, this.$actors, continuation);
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
                    String str = this.$poster;
                    $this$newMovieLoadResponse.setPosterUrl(str != null ? StringsKt.trim(str).toString() : null);
                    $this$newMovieLoadResponse.setYear(this.$year);
                    $this$newMovieLoadResponse.setPlot(this.$description);
                    $this$newMovieLoadResponse.setTags(this.$tags);
                    $this$newMovieLoadResponse.setScore(this.$score);
                    $this$newMovieLoadResponse.setActors(this.$actors);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0237 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r24, boolean r25, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r26, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r27, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r28) {
        /*
            Method dump skipped, instruction units count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.EinthusanProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$loadLinks$3, reason: invalid class name */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider$loadLinks$3", f = "EinthusanProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = EinthusanProvider.this.new AnonymousClass3(continuation);
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
                    $this$newExtractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Referer", EinthusanProvider.this.getMainUrl() + '/')));
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.EinthusanProvider$loadLinks$4, reason: invalid class name */
    /* JADX INFO: compiled from: EinthusanProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.EinthusanProvider$loadLinks$4", f = "EinthusanProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = EinthusanProvider.this.new AnonymousClass4(continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
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
                    $this$newExtractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Referer", EinthusanProvider.this.getMainUrl() + '/')));
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
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
