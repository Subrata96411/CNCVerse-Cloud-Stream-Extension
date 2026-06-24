package com.hexated;

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
import com.lagradost.cloudstream3.Actor;
import com.lagradost.cloudstream3.AnimeSearchResponse;
import com.lagradost.cloudstream3.Episode;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchResponse;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorApiKt;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Element;

/* JADX INFO: compiled from: HDrezkaProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nHDrezkaProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HDrezkaProvider.kt\ncom/hexated/HDrezkaProvider\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 NiceResponse.kt\ncom/lagradost/nicehttp/NiceResponse\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 6 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 7 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,704:1\n1586#2:705\n1661#2,3:706\n1586#2:709\n1661#2,3:710\n1586#2:713\n1661#2,3:714\n1642#2,10:723\n1915#2:733\n1916#2:735\n1652#2:736\n1586#2:737\n1661#2,3:738\n1586#2:741\n1661#2,3:742\n1586#2:745\n1661#2,3:746\n1586#2:749\n1661#2,3:750\n1586#2:753\n1661#2,3:754\n1915#2,2:757\n1586#2:759\n1661#2,2:760\n1586#2:762\n1661#2,3:763\n1663#2:766\n1586#2:767\n1661#2,3:768\n1586#2:794\n1661#2,2:795\n1663#2:820\n1586#2:821\n1661#2,2:822\n1266#2,4:833\n1663#2:842\n2829#2,5:843\n1915#2:848\n1915#2,2:849\n1916#2:851\n2835#2:852\n67#3,5:717\n67#3,5:837\n1#4:722\n1#4:734\n1#4:774\n1#4:800\n93#5,2:771\n63#5:773\n64#5,15:775\n95#5,2:792\n93#5,2:797\n63#5:799\n64#5,15:801\n95#5,2:818\n50#6:790\n43#6:791\n50#6:816\n43#6:817\n507#7,7:824\n466#7:831\n415#7:832\n*S KotlinDebug\n*F\n+ 1 HDrezkaProvider.kt\ncom/hexated/HDrezkaProvider\n*L\n64#1:705\n64#1:706,3\n102#1:709\n102#1:710,3\n117#1:713\n117#1:714,3\n134#1:723,10\n134#1:733\n134#1:735\n134#1:736\n141#1:737\n141#1:738,3\n155#1:741\n155#1:742,3\n165#1:745\n165#1:746,3\n179#1:749\n179#1:750,3\n212#1:753\n212#1:754,3\n265#1:757,2\n323#1:759\n323#1:760,2\n328#1:762\n328#1:763,3\n323#1:766\n341#1:767\n341#1:768,3\n380#1:794\n380#1:795,2\n380#1:820\n396#1:821\n396#1:822,2\n409#1:833,4\n396#1:842\n250#1:843,5\n252#1:848\n253#1:849,2\n252#1:851\n250#1:852\n127#1:717,5\n411#1:837,5\n134#1:734\n377#1:774\n384#1:800\n377#1:771,2\n377#1:773\n377#1:775,15\n377#1:792,2\n384#1:797,2\n384#1:799\n384#1:801,15\n384#1:818,2\n377#1:790\n377#1:791\n384#1:816\n384#1:817\n409#1:824,7\n409#1:831\n409#1:832\n*E\n"})
public final class HDrezkaProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://rezka.ag";

    @NotNull
    private String name = "HDrezka";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ru";
    private final boolean hasDownloadSupport = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries, TvType.Anime, TvType.AsianDrama});

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to(getMainUrl() + "/films/?filter=watching", "фильмы"), TuplesKt.to(getMainUrl() + "/series/?filter=watching", "сериалы"), TuplesKt.to(getMainUrl() + "/cartoons/?filter=watching", "мультфильмы"), TuplesKt.to(getMainUrl() + "/animation/?filter=watching", "аниме")});

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$cleanCallback$1, reason: invalid class name */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider", f = "HDrezkaProvider.kt", i = {0, 0, 0, 0, 0}, l = {282}, m = "cleanCallback", n = {"source", "url", "quality", "sourceCallback", "isM3u8"}, nl = {281}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDrezkaProvider.this.cleanCallback(null, null, null, false, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider", f = "HDrezkaProvider.kt", i = {0, 0, 0}, l = {62}, m = "getMainPage", n = {"request", "url", "page"}, nl = {63}, s = {"L$0", "L$1", "I$0"}, v = 2)
    static final class C00001 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00001(Continuation<? super C00001> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDrezkaProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$invokeSources$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider", f = "HDrezkaProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {331, 346}, m = "invokeSources", n = {"source", "url", "subtitle", "subCallback", "sourceCallback", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "links", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "quality", "item$iv$iv", "it", "link", "type", "$i$f$map", "$i$f$mapTo", "$i$a$-map-HDrezkaProvider$invokeSources$2", "$i$f$map", "$i$f$mapTo", "$i$a$-map-HDrezkaProvider$invokeSources$2$1", "source", "url", "subtitle", "subCallback", "sourceCallback", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "sub", "link", "language", "$i$f$map", "$i$f$mapTo", "$i$a$-map-HDrezkaProvider$invokeSources$3"}, nl = {338, 345}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$16", "L$17", "L$19", "L$20", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$11", "L$12", "I$0", "I$1", "I$2"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
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
        Object L$20;
        Object L$21;
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
            return HDrezkaProvider.this.invokeSources(null, null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider", f = "HDrezkaProvider.kt", i = {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {109, 123, 201, 227}, m = "load", n = {"url", "url", "document", "id", "title", "poster", "tags", "year", "tvType", "description", "url", "document", "id", "title", "poster", "tags", "year", "tvType", "description", "trailer", "ratingText", "score", "actors", "recommendations", "data", "server", "translators", "episodes", "url", "document", "id", "title", "poster", "tags", "year", "tvType", "description", "trailer", "ratingText", "score", "actors", "recommendations", "data", "server"}, nl = {111, 127, 212, 152}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$17", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15"}, v = 2)
    static final class C00021 extends ContinuationImpl {
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
            return HDrezkaProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider", f = "HDrezkaProvider.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {379, 385, 397, 412}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "res", "isCasting", "$i$a$-let-HDrezkaProvider$loadLinks$3", "data", "subtitleCallback", "callback", "res", "document", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "script", "dataJson", "source", "isCasting", "$i$a$-let-HDrezkaProvider$loadLinks$3", "$i$f$map", "$i$f$mapTo", "$i$a$-map-HDrezkaProvider$loadLinks$3$1", "$i$a$-let-HDrezkaProvider$loadLinks$3$1$1", "data", "subtitleCallback", "callback", "res", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "server", "isCasting", "$i$a$-let-HDrezkaProvider$loadLinks$3", "$i$f$map", "$i$f$mapTo", "$i$a$-map-HDrezkaProvider$loadLinks$3$2", "data", "subtitleCallback", "callback", "res", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "server", "source", "isCasting", "$i$a$-let-HDrezkaProvider$loadLinks$3", "$i$f$map", "$i$f$mapTo", "$i$a$-map-HDrezkaProvider$loadLinks$3$2", "$i$a$-let-HDrezkaProvider$loadLinks$3$2$3"}, nl = {380, 392, 411, 419}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$12", "L$13", "Z$0", "I$0", "I$1", "I$2", "I$3", "I$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "Z$0", "I$0", "I$1", "I$2", "I$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10", "Z$0", "I$0", "I$1", "I$2", "I$3", "I$4"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
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

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDrezkaProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider", f = "HDrezkaProvider.kt", i = {0, 0}, l = {100}, m = "search", n = {"query", "link"}, nl = {102}, s = {"L$0", "L$1"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HDrezkaProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/hexated/HDrezkaProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "HDrezkaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return HDrezkaProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            HDrezkaProvider.context = context;
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

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r25, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r26, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r27) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hexated.HDrezkaProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final SearchResponse toSearchResult(Element $this$toSearchResult) {
        String strText;
        Element elementSelectFirst = $this$toSearchResult.selectFirst("div.b-content__inline_item-link > a");
        String title = String.valueOf((elementSelectFirst == null || (strText = elementSelectFirst.text()) == null) ? null : StringsKt.trim(strText).toString());
        Element elementSelectFirst2 = $this$toSearchResult.selectFirst("a");
        String href = String.valueOf(elementSelectFirst2 != null ? elementSelectFirst2.attr("href") : null);
        final String posterUrl = $this$toSearchResult.select("img").attr("src");
        TvType type = !$this$toSearchResult.select("span.info").isEmpty() ? TvType.TvSeries : TvType.Movie;
        if (type == TvType.Movie) {
            return MainAPIKt.newMovieSearchResponse$default(this, title, href, TvType.Movie, false, new Function1() { // from class: com.hexated.HDrezkaProvider$$ExternalSyntheticLambda5
                public final Object invoke(Object obj) {
                    return HDrezkaProvider.toSearchResult$lambda$0(posterUrl, (MovieSearchResponse) obj);
                }
            }, 8, (Object) null);
        }
        final Integer episode = StringsKt.toIntOrNull(new Regex("[^0-9]").replace(StringsKt.substringAfter$default($this$toSearchResult.select("span.info").text(), ",", (String) null, 2, (Object) null), ""));
        return MainAPIKt.newAnimeSearchResponse$default(this, title, href, TvType.TvSeries, false, new Function1() { // from class: com.hexated.HDrezkaProvider$$ExternalSyntheticLambda6
            public final Object invoke(Object obj) {
                return HDrezkaProvider.toSearchResult$lambda$1(posterUrl, episode, (AnimeSearchResponse) obj);
            }
        }, 8, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResult$lambda$0(String $posterUrl, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl($posterUrl);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toSearchResult$lambda$1(String $posterUrl, Integer $episode, AnimeSearchResponse $this$newAnimeSearchResponse) {
        $this$newAnimeSearchResponse.setPosterUrl($posterUrl);
        MainAPIKt.addDubStatus($this$newAnimeSearchResponse, true, true, $episode, $episode);
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
            Method dump skipped, instruction units count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hexated.HDrezkaProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0427 A[LOOP:1: B:98:0x0421->B:100:0x0427, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0495  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0749  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03f0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x021f A[LOOP:6: B:37:0x0219->B:39:0x021f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0306 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x03ec  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r43, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r44) {
        /*
            Method dump skipped, instruction units count: 2242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hexated.HDrezkaProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit load$lambda$7$1(String $name, Integer $season, Integer $episode, Episode $this$newEpisode) {
        $this$newEpisode.setName($name);
        $this$newEpisode.setSeason($season);
        $this$newEpisode.setEpisode($episode);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$load$4, reason: invalid class name */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider$load$4", f = "HDrezkaProvider.kt", i = {0}, l = {209}, m = "invokeSuspend", n = {"$this$newTvSeriesLoadResponse"}, nl = {210}, s = {"L$0"}, v = 2)
    static final class AnonymousClass4 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Actor> $actors;
        final /* synthetic */ String $description;
        final /* synthetic */ String $poster;
        final /* synthetic */ List<SearchResponse> $recommendations;
        final /* synthetic */ Score $score;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ String $trailer;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(String str, Integer num, String str2, List<String> list, Score score, List<Actor> list2, List<? extends SearchResponse> list3, String str3, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$description = str2;
            this.$tags = list;
            this.$score = score;
            this.$actors = list2;
            this.$recommendations = list3;
            this.$trailer = str3;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$poster, this.$year, this.$description, this.$tags, this.$score, this.$actors, this.$recommendations, this.$trailer, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return create(tvSeriesLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            LoadResponse loadResponse = (TvSeriesLoadResponse) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    loadResponse.setPosterUrl(this.$poster);
                    loadResponse.setYear(this.$year);
                    loadResponse.setPlot(this.$description);
                    loadResponse.setTags(this.$tags);
                    loadResponse.setScore(this.$score);
                    LoadResponse.Companion.addActorsOnly(loadResponse, this.$actors);
                    loadResponse.setRecommendations(this.$recommendations);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                    this.label = 1;
                    if (LoadResponse.Companion.addTrailer$default(LoadResponse.Companion, loadResponse, this.$trailer, (String) null, false, (Continuation) this, 6, (Object) null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$load$6, reason: invalid class name */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider$load$6", f = "HDrezkaProvider.kt", i = {0}, l = {235}, m = "invokeSuspend", n = {"$this$newMovieLoadResponse"}, nl = {236}, s = {"L$0"}, v = 2)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<Actor> $actors;
        final /* synthetic */ String $description;
        final /* synthetic */ String $poster;
        final /* synthetic */ List<SearchResponse> $recommendations;
        final /* synthetic */ Score $score;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ String $trailer;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(String str, Integer num, String str2, List<String> list, Score score, List<Actor> list2, List<? extends SearchResponse> list3, String str3, Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$year = num;
            this.$description = str2;
            this.$tags = list;
            this.$score = score;
            this.$actors = list2;
            this.$recommendations = list3;
            this.$trailer = str3;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass6 = new AnonymousClass6(this.$poster, this.$year, this.$description, this.$tags, this.$score, this.$actors, this.$recommendations, this.$trailer, continuation);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return create(movieLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            LoadResponse loadResponse = (MovieLoadResponse) this.L$0;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    loadResponse.setPosterUrl(this.$poster);
                    loadResponse.setYear(this.$year);
                    loadResponse.setPlot(this.$description);
                    loadResponse.setTags(this.$tags);
                    loadResponse.setScore(this.$score);
                    LoadResponse.Companion.addActorsOnly(loadResponse, this.$actors);
                    loadResponse.setRecommendations(this.$recommendations);
                    this.L$0 = SpillingKt.nullOutSpilledVariable(loadResponse);
                    this.label = 1;
                    if (LoadResponse.Companion.addTrailer$default(LoadResponse.Companion, loadResponse, this.$trailer, (String) null, false, (Continuation) this, 6, (Object) null) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    private final String decryptStreamUrl(String data) {
        if (StringsKt.startsWith$default(data, "[", false, 2, (Object) null)) {
            return data;
        }
        List trashList = CollectionsKt.listOf(new String[]{"@", "#", "!", "^", "$"});
        Iterable trashSet = CollectionsKt.plus(decryptStreamUrl$getTrash(trashList, 2), decryptStreamUrl$getTrash(trashList, 3));
        String strJoinToString$default = CollectionsKt.joinToString$default(StringsKt.split$default(StringsKt.replace$default(data, "#h", "", false, 4, (Object) null), new String[]{"//_//"}, false, 0, 6, (Object) null), "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        Iterable $this$forEach$iv = trashSet;
        String strReplace$default = strJoinToString$default;
        for (Object element$iv : $this$forEach$iv) {
            String it = (String) element$iv;
            byte[] bytes = it.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            String temp = MainAPIKt.base64Encode(bytes);
            strReplace$default = StringsKt.replace$default(strReplace$default, temp, "", false, 4, (Object) null);
        }
        return MainAPIKt.base64Decode(strReplace$default);
    }

    private static final List<String> decryptStreamUrl$getTrash(List<String> list, int item) {
        ArrayList trash = new ArrayList();
        int i = 1;
        if (1 <= item) {
            while (true) {
                trash.add(list);
                if (i == item) {
                    break;
                }
                i++;
            }
        }
        ArrayList $this$reduce$iv = trash;
        Iterator iterator$iv = $this$reduce$iv.iterator();
        if (!iterator$iv.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object accumulator$iv = iterator$iv.next();
        while (iterator$iv.hasNext()) {
            Iterable list2 = (List) iterator$iv.next();
            Iterable acc = (List) accumulator$iv;
            ArrayList temp = new ArrayList();
            Iterable $this$forEach$iv = acc;
            for (Object element$iv : $this$forEach$iv) {
                String ac = (String) element$iv;
                Iterable $this$forEach$iv2 = list2;
                for (Object element$iv2 : $this$forEach$iv2) {
                    String li = (String) element$iv2;
                    temp.add(ac + li);
                    trash = trash;
                }
            }
            accumulator$iv = temp;
        }
        return (List) accumulator$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object cleanCallback(java.lang.String r9, java.lang.String r10, java.lang.String r11, boolean r12, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r8 = this;
            boolean r0 = r14 instanceof com.hexated.HDrezkaProvider.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r14
            com.hexated.HDrezkaProvider$cleanCallback$1 r0 = (com.hexated.HDrezkaProvider.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.hexated.HDrezkaProvider$cleanCallback$1 r0 = new com.hexated.HDrezkaProvider$cleanCallback$1
            r0.<init>(r14)
        L19:
            r6 = r0
            java.lang.Object r0 = r6.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            switch(r1) {
                case 0: goto L51;
                case 1: goto L2f;
                default: goto L25;
            }
        L25:
            r1 = r9
            r3 = r10
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2f:
            boolean r12 = r6.Z$0
            java.lang.Object r1 = r6.L$4
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            java.lang.Object r2 = r6.L$3
            r13 = r2
            kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
            java.lang.Object r2 = r6.L$2
            r11 = r2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r2 = r6.L$1
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r2 = r6.L$0
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r13
            r13 = r1
            r1 = r9
            r9 = r0
            goto L94
        L51:
            kotlin.ResultKt.throwOnFailure(r0)
            if (r12 == 0) goto L5d
            com.lagradost.cloudstream3.utils.ExtractorLinkType r1 = com.lagradost.cloudstream3.utils.ExtractorLinkType.M3U8
            goto L5f
        L5d:
            com.lagradost.cloudstream3.utils.ExtractorLinkType r1 = com.lagradost.cloudstream3.utils.ExtractorLinkType.VIDEO
        L5f:
            r4 = r1
            com.hexated.HDrezkaProvider$cleanCallback$2 r1 = new com.hexated.HDrezkaProvider$cleanCallback$2
            r2 = 0
            r1.<init>(r11, r2)
            r5 = r1
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r9)
            r6.L$0 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r10)
            r6.L$1 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r11)
            r6.L$2 = r1
            java.lang.Object r1 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r13)
            r6.L$3 = r1
            r6.L$4 = r13
            r6.Z$0 = r12
            r1 = 1
            r6.label = r1
            r2 = r9
            r1 = r9
            r3 = r10
            java.lang.Object r9 = com.lagradost.cloudstream3.utils.ExtractorApiKt.newExtractorLink(r1, r2, r3, r4, r5, r6)
            if (r9 != r7) goto L92
            return r7
        L92:
            r2 = r13
            r10 = r3
        L94:
            r13.invoke(r9)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hexated.HDrezkaProvider.cleanCallback(java.lang.String, java.lang.String, java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.hexated.HDrezkaProvider$cleanCallback$2, reason: invalid class name */
    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hexated.HDrezkaProvider$cleanCallback$2", f = "HDrezkaProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $quality;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$quality = str;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = HDrezkaProvider.this.new AnonymousClass2(this.$quality, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
                    $this$newExtractorLink.setReferer(HDrezkaProvider.this.getMainUrl() + '/');
                    $this$newExtractorLink.setQuality(HDrezkaProvider.this.getQuality(this.$quality));
                    $this$newExtractorLink.setHeaders(MapsKt.mapOf(TuplesKt.to("Origin", HDrezkaProvider.this.getMainUrl())));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private final String getLanguage(String str) {
        return Intrinsics.areEqual(str, "Русский") ? "Russian" : Intrinsics.areEqual(str, "Українська") ? "Ukrainian" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final int getQuality(String str) {
        switch (str.hashCode()) {
            case -1762539867:
                if (str.equals("1080p Ultra")) {
                    return Qualities.P1080.getValue();
                }
                break;
            case 1572835:
                if (str.equals("360p")) {
                    return Qualities.P240.getValue();
                }
                break;
            case 1604548:
                if (str.equals("480p")) {
                    return Qualities.P360.getValue();
                }
                break;
            case 1688155:
                if (str.equals("720p")) {
                    return Qualities.P480.getValue();
                }
                break;
            case 46737913:
                if (str.equals("1080p")) {
                    return Qualities.P720.getValue();
                }
                break;
        }
        return ExtractorApiKt.getQualityFromName(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Path cross not found for [B:39:0x0406, B:21:0x01ca], limit reached: 64 */
    /* JADX WARN: Removed duplicated region for block: B:17:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0420  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x058c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x01e1 -> B:26:0x0279). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x0372 -> B:36:0x039f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x0564 -> B:56:0x0567). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSources(java.lang.String r39, java.lang.String r40, java.lang.String r41, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r42, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r43, kotlin.coroutines.Continuation<? super kotlin.Unit> r44) {
        /*
            Method dump skipped, instruction units count: 1440
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hexated.HDrezkaProvider.invokeSources(java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:189|190|222|191|192|233|193|194|236|195|204|(2:206|(1:208)(5:209|210|212|170|(1:213)(0)))(4:211|212|170|(0)(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(14:110|228|111|115|(1:117)(5:224|118|119|220|120)|127|(1:129)|230|130|131|(6:246|133|134|248|135|155)(1:146)|147|148|155) */
    /* JADX WARN: Can't wrap try/catch for region: R(5:(1:224)|118|119|220|120) */
    /* JADX WARN: Can't wrap try/catch for region: R(6:246|133|134|248|135|155) */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x0444, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x044b, code lost:
    
        r4 = kotlin.Result.Companion;
        r0 = kotlin.Result.constructor-impl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x0472, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x047b, code lost:
    
        com.lagradost.cloudstream3.mvvm.ArchComponentExtKt.logError((java.lang.Throwable) r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x0846, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0848, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x0849, code lost:
    
        r58 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x084c, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x084d, code lost:
    
        r58 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0851, code lost:
    
        r0.printStackTrace();
        r0 = null;
     */
    /* JADX WARN: Path cross not found for [B:244:0x0298, B:77:0x02ad], limit reached: 238 */
    /* JADX WARN: Removed duplicated region for block: B:105:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0482  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x08f4  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0929  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x095c  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0462 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02d0  */
    /* JADX WARN: Type inference failed for: r0v26, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v83, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v87, types: [java.lang.Throwable] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:161:0x052b -> B:162:0x054a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:163:0x056a -> B:165:0x05b3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:164:0x058e -> B:165:0x05b3). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:209:0x08ca -> B:210:0x08e2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:211:0x08f4 -> B:212:0x0913). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r54, boolean r55, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r56, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r57, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r58) {
        /*
            Method dump skipped, instruction units count: 2434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hexated.HDrezkaProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/hexated/HDrezkaProvider$LocalSources;", "", "streams", "", "subtitle", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getStreams", "()Ljava/lang/String;", "getSubtitle", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "HDrezkaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LocalSources {

        @JsonProperty("streams")
        @NotNull
        private final String streams;

        @JsonProperty("subtitle")
        @Nullable
        private final Object subtitle;

        public static /* synthetic */ LocalSources copy$default(LocalSources localSources, String str, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                str = localSources.streams;
            }
            if ((i & 2) != 0) {
                obj = localSources.subtitle;
            }
            return localSources.copy(str, obj);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStreams() {
            return this.streams;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Object getSubtitle() {
            return this.subtitle;
        }

        @NotNull
        public final LocalSources copy(@JsonProperty("streams") @NotNull String streams, @JsonProperty("subtitle") @Nullable Object subtitle) {
            return new LocalSources(streams, subtitle);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LocalSources)) {
                return false;
            }
            LocalSources localSources = (LocalSources) other;
            return Intrinsics.areEqual(this.streams, localSources.streams) && Intrinsics.areEqual(this.subtitle, localSources.subtitle);
        }

        public int hashCode() {
            return (this.streams.hashCode() * 31) + (this.subtitle == null ? 0 : this.subtitle.hashCode());
        }

        @NotNull
        public String toString() {
            return "LocalSources(streams=" + this.streams + ", subtitle=" + this.subtitle + ')';
        }

        public LocalSources(@JsonProperty("streams") @NotNull String streams, @JsonProperty("subtitle") @Nullable Object subtitle) {
            this.streams = streams;
            this.subtitle = subtitle;
        }

        @NotNull
        public final String getStreams() {
            return this.streams;
        }

        @Nullable
        public final Object getSubtitle() {
            return this.subtitle;
        }
    }

    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00018\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/hexated/HDrezkaProvider$Sources;", "", "url", "", "subtitle", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getUrl", "()Ljava/lang/String;", "getSubtitle", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "HDrezkaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Sources {

        @JsonProperty("subtitle")
        @Nullable
        private final Object subtitle;

        @JsonProperty("url")
        @NotNull
        private final String url;

        public static /* synthetic */ Sources copy$default(Sources sources, String str, Object obj, int i, Object obj2) {
            if ((i & 1) != 0) {
                str = sources.url;
            }
            if ((i & 2) != 0) {
                obj = sources.subtitle;
            }
            return sources.copy(str, obj);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Object getSubtitle() {
            return this.subtitle;
        }

        @NotNull
        public final Sources copy(@JsonProperty("url") @NotNull String url, @JsonProperty("subtitle") @Nullable Object subtitle) {
            return new Sources(url, subtitle);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Sources)) {
                return false;
            }
            Sources sources = (Sources) other;
            return Intrinsics.areEqual(this.url, sources.url) && Intrinsics.areEqual(this.subtitle, sources.subtitle);
        }

        public int hashCode() {
            return (this.url.hashCode() * 31) + (this.subtitle == null ? 0 : this.subtitle.hashCode());
        }

        @NotNull
        public String toString() {
            return "Sources(url=" + this.url + ", subtitle=" + this.subtitle + ')';
        }

        public Sources(@JsonProperty("url") @NotNull String url, @JsonProperty("subtitle") @Nullable Object subtitle) {
            this.url = url;
            this.subtitle = subtitle;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Object getSubtitle() {
            return this.subtitle;
        }
    }

    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003JE\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/hexated/HDrezkaProvider$Server;", "", "translator_name", "", "translator_id", "camrip", "ads", "director", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTranslator_name", "()Ljava/lang/String;", "getTranslator_id", "getCamrip", "getAds", "getDirector", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "HDrezkaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Server {

        @JsonProperty("ads")
        @Nullable
        private final String ads;

        @JsonProperty("camrip")
        @Nullable
        private final String camrip;

        @JsonProperty("director")
        @Nullable
        private final String director;

        @JsonProperty("translator_id")
        @Nullable
        private final String translator_id;

        @JsonProperty("translator_name")
        @Nullable
        private final String translator_name;

        public static /* synthetic */ Server copy$default(Server server, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                str = server.translator_name;
            }
            if ((i & 2) != 0) {
                str2 = server.translator_id;
            }
            if ((i & 4) != 0) {
                str3 = server.camrip;
            }
            if ((i & 8) != 0) {
                str4 = server.ads;
            }
            if ((i & 16) != 0) {
                str5 = server.director;
            }
            String str6 = str5;
            String str7 = str3;
            return server.copy(str, str2, str7, str4, str6);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTranslator_name() {
            return this.translator_name;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTranslator_id() {
            return this.translator_id;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCamrip() {
            return this.camrip;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAds() {
            return this.ads;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getDirector() {
            return this.director;
        }

        @NotNull
        public final Server copy(@JsonProperty("translator_name") @Nullable String translator_name, @JsonProperty("translator_id") @Nullable String translator_id, @JsonProperty("camrip") @Nullable String camrip, @JsonProperty("ads") @Nullable String ads, @JsonProperty("director") @Nullable String director) {
            return new Server(translator_name, translator_id, camrip, ads, director);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Server)) {
                return false;
            }
            Server server = (Server) other;
            return Intrinsics.areEqual(this.translator_name, server.translator_name) && Intrinsics.areEqual(this.translator_id, server.translator_id) && Intrinsics.areEqual(this.camrip, server.camrip) && Intrinsics.areEqual(this.ads, server.ads) && Intrinsics.areEqual(this.director, server.director);
        }

        public int hashCode() {
            return ((((((((this.translator_name == null ? 0 : this.translator_name.hashCode()) * 31) + (this.translator_id == null ? 0 : this.translator_id.hashCode())) * 31) + (this.camrip == null ? 0 : this.camrip.hashCode())) * 31) + (this.ads == null ? 0 : this.ads.hashCode())) * 31) + (this.director != null ? this.director.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Server(translator_name=" + this.translator_name + ", translator_id=" + this.translator_id + ", camrip=" + this.camrip + ", ads=" + this.ads + ", director=" + this.director + ')';
        }

        public Server(@JsonProperty("translator_name") @Nullable String translator_name, @JsonProperty("translator_id") @Nullable String translator_id, @JsonProperty("camrip") @Nullable String camrip, @JsonProperty("ads") @Nullable String ads, @JsonProperty("director") @Nullable String director) {
            this.translator_name = translator_name;
            this.translator_id = translator_id;
            this.camrip = camrip;
            this.ads = ads;
            this.director = director;
        }

        @Nullable
        public final String getTranslator_name() {
            return this.translator_name;
        }

        @Nullable
        public final String getTranslator_id() {
            return this.translator_id;
        }

        @Nullable
        public final String getCamrip() {
            return this.camrip;
        }

        @Nullable
        public final String getAds() {
            return this.ads;
        }

        @Nullable
        public final String getDirector() {
            return this.director;
        }
    }

    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0001\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jc\u0010\u001e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000f¨\u0006%"}, d2 = {"Lcom/hexated/HDrezkaProvider$Data;", "", "id", "", "favs", "server", "", "Lcom/hexated/HDrezkaProvider$Server;", "season", "episode", "action", "ref", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getFavs", "getServer", "()Ljava/util/List;", "getSeason", "getEpisode", "getAction", "getRef", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "HDrezkaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Data {

        @JsonProperty("action")
        @Nullable
        private final String action;

        @JsonProperty("episode")
        @Nullable
        private final String episode;

        @JsonProperty("favs")
        @Nullable
        private final String favs;

        @JsonProperty("id")
        @Nullable
        private final String id;

        @JsonProperty("ref")
        @Nullable
        private final String ref;

        @JsonProperty("season")
        @Nullable
        private final String season;

        @JsonProperty("server")
        @Nullable
        private final List<Server> server;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Data copy$default(Data data, String str, String str2, List list, String str3, String str4, String str5, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = data.id;
            }
            if ((i & 2) != 0) {
                str2 = data.favs;
            }
            if ((i & 4) != 0) {
                list = data.server;
            }
            if ((i & 8) != 0) {
                str3 = data.season;
            }
            if ((i & 16) != 0) {
                str4 = data.episode;
            }
            if ((i & 32) != 0) {
                str5 = data.action;
            }
            if ((i & 64) != 0) {
                str6 = data.ref;
            }
            String str7 = str5;
            String str8 = str6;
            String str9 = str4;
            List list2 = list;
            return data.copy(str, str2, list2, str3, str9, str7, str8);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFavs() {
            return this.favs;
        }

        @Nullable
        public final List<Server> component3() {
            return this.server;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getEpisode() {
            return this.episode;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getRef() {
            return this.ref;
        }

        @NotNull
        public final Data copy(@JsonProperty("id") @Nullable String id, @JsonProperty("favs") @Nullable String favs, @JsonProperty("server") @Nullable List<Server> server, @JsonProperty("season") @Nullable String season, @JsonProperty("episode") @Nullable String episode, @JsonProperty("action") @Nullable String action, @JsonProperty("ref") @Nullable String ref) {
            return new Data(id, favs, server, season, episode, action, ref);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Data)) {
                return false;
            }
            Data data = (Data) other;
            return Intrinsics.areEqual(this.id, data.id) && Intrinsics.areEqual(this.favs, data.favs) && Intrinsics.areEqual(this.server, data.server) && Intrinsics.areEqual(this.season, data.season) && Intrinsics.areEqual(this.episode, data.episode) && Intrinsics.areEqual(this.action, data.action) && Intrinsics.areEqual(this.ref, data.ref);
        }

        public int hashCode() {
            return ((((((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.favs == null ? 0 : this.favs.hashCode())) * 31) + (this.server == null ? 0 : this.server.hashCode())) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode == null ? 0 : this.episode.hashCode())) * 31) + (this.action == null ? 0 : this.action.hashCode())) * 31) + (this.ref != null ? this.ref.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Data(id=" + this.id + ", favs=" + this.favs + ", server=" + this.server + ", season=" + this.season + ", episode=" + this.episode + ", action=" + this.action + ", ref=" + this.ref + ')';
        }

        public Data(@JsonProperty("id") @Nullable String id, @JsonProperty("favs") @Nullable String favs, @JsonProperty("server") @Nullable List<Server> list, @JsonProperty("season") @Nullable String season, @JsonProperty("episode") @Nullable String episode, @JsonProperty("action") @Nullable String action, @JsonProperty("ref") @Nullable String ref) {
            this.id = id;
            this.favs = favs;
            this.server = list;
            this.season = season;
            this.episode = episode;
            this.action = action;
            this.ref = ref;
        }

        @Nullable
        public final String getId() {
            return this.id;
        }

        @Nullable
        public final String getFavs() {
            return this.favs;
        }

        @Nullable
        public final List<Server> getServer() {
            return this.server;
        }

        @Nullable
        public final String getSeason() {
            return this.season;
        }

        @Nullable
        public final String getEpisode() {
            return this.episode;
        }

        @Nullable
        public final String getAction() {
            return this.action;
        }

        @Nullable
        public final String getRef() {
            return this.ref;
        }
    }

    /* JADX INFO: compiled from: HDrezkaProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0001\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/hexated/HDrezkaProvider$Trailer;", "", "success", "", "code", "", "<init>", "(Ljava/lang/Boolean;Ljava/lang/String;)V", "getSuccess", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCode", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Boolean;Ljava/lang/String;)Lcom/hexated/HDrezkaProvider$Trailer;", "equals", "other", "hashCode", "", "toString", "HDrezkaProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Trailer {

        @JsonProperty("code")
        @Nullable
        private final String code;

        @JsonProperty("success")
        @Nullable
        private final Boolean success;

        public static /* synthetic */ Trailer copy$default(Trailer trailer, Boolean bool, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                bool = trailer.success;
            }
            if ((i & 2) != 0) {
                str = trailer.code;
            }
            return trailer.copy(bool, str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Boolean getSuccess() {
            return this.success;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCode() {
            return this.code;
        }

        @NotNull
        public final Trailer copy(@JsonProperty("success") @Nullable Boolean success, @JsonProperty("code") @Nullable String code) {
            return new Trailer(success, code);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Trailer)) {
                return false;
            }
            Trailer trailer = (Trailer) other;
            return Intrinsics.areEqual(this.success, trailer.success) && Intrinsics.areEqual(this.code, trailer.code);
        }

        public int hashCode() {
            return ((this.success == null ? 0 : this.success.hashCode()) * 31) + (this.code != null ? this.code.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "Trailer(success=" + this.success + ", code=" + this.code + ')';
        }

        public Trailer(@JsonProperty("success") @Nullable Boolean success, @JsonProperty("code") @Nullable String code) {
            this.success = success;
            this.code = code;
        }

        @Nullable
        public final Boolean getSuccess() {
            return this.success;
        }

        @Nullable
        public final String getCode() {
            return this.code;
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
