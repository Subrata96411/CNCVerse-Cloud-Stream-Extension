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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.AnimeSearchResponse;
import com.lagradost.cloudstream3.AudioFile;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.MainPageData;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
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
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: BilibiliProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nBilibiliProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BilibiliProvider.kt\ncom/cncverse/BilibiliProvider\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,1640:1\n63#2:1641\n64#2,15:1643\n63#2:1660\n64#2,15:1662\n63#2:1683\n64#2,15:1685\n63#2:1706\n64#2,15:1708\n63#2:1729\n64#2,15:1731\n63#2:1752\n64#2,15:1754\n63#2:1777\n64#2,15:1779\n63#2:1796\n64#2,15:1798\n63#2:1833\n64#2,15:1835\n63#2:1852\n64#2,15:1854\n63#2:1878\n64#2,15:1880\n63#2:1914\n64#2,15:1916\n1#3:1642\n1#3:1661\n1#3:1684\n1#3:1707\n1#3:1730\n1#3:1753\n1#3:1778\n1#3:1797\n1#3:1830\n1#3:1834\n1#3:1853\n1#3:1879\n1#3:1915\n1#3:1935\n50#4:1658\n43#4:1659\n50#4:1677\n43#4:1678\n50#4:1700\n43#4:1701\n50#4:1723\n43#4:1724\n50#4:1746\n43#4:1747\n50#4:1769\n43#4:1770\n50#4:1794\n43#4:1795\n50#4:1813\n43#4:1814\n50#4:1850\n43#4:1851\n50#4:1869\n43#4:1870\n50#4:1895\n43#4:1896\n50#4:1931\n43#4:1932\n1915#5:1679\n1915#5,2:1680\n1916#5:1682\n1915#5:1702\n1915#5,2:1703\n1916#5:1705\n1915#5:1725\n1915#5,2:1726\n1916#5:1728\n1915#5:1748\n1915#5,2:1749\n1916#5:1751\n1915#5:1771\n1915#5,2:1772\n1915#5,2:1774\n1916#5:1776\n1915#5:1815\n1915#5,2:1816\n1916#5:1818\n1642#5,10:1819\n1915#5:1829\n1916#5:1831\n1652#5:1832\n1924#5,3:1871\n1915#5,2:1874\n1915#5,2:1897\n1807#5,3:1907\n1915#5:1910\n1916#5:1913\n1915#5,2:1933\n1342#6,2:1876\n1342#6,2:1899\n1342#6,2:1901\n1342#6,2:1903\n1342#6,2:1905\n1342#6,2:1911\n*S KotlinDebug\n*F\n+ 1 BilibiliProvider.kt\ncom/cncverse/BilibiliProvider\n*L\n79#1:1641\n79#1:1643,15\n137#1:1660\n137#1:1662,15\n166#1:1683\n166#1:1685,15\n189#1:1706\n189#1:1708,15\n217#1:1729\n217#1:1731,15\n253#1:1752\n253#1:1754,15\n312#1:1777\n312#1:1779,15\n322#1:1796\n322#1:1798,15\n685#1:1833\n685#1:1835,15\n789#1:1852\n789#1:1854,15\n1096#1:1878\n1096#1:1880,15\n1328#1:1914\n1328#1:1916,15\n79#1:1642\n137#1:1661\n166#1:1684\n189#1:1707\n217#1:1730\n253#1:1753\n312#1:1778\n322#1:1797\n367#1:1830\n685#1:1834\n789#1:1853\n1096#1:1879\n1328#1:1915\n79#1:1658\n79#1:1659\n137#1:1677\n137#1:1678\n166#1:1700\n166#1:1701\n189#1:1723\n189#1:1724\n217#1:1746\n217#1:1747\n253#1:1769\n253#1:1770\n312#1:1794\n312#1:1795\n322#1:1813\n322#1:1814\n685#1:1850\n685#1:1851\n789#1:1869\n789#1:1870\n1096#1:1895\n1096#1:1896\n1328#1:1931\n1328#1:1932\n140#1:1679\n141#1:1680,2\n140#1:1682\n169#1:1702\n170#1:1703,2\n169#1:1705\n192#1:1725\n193#1:1726,2\n192#1:1728\n219#1:1748\n220#1:1749,2\n219#1:1751\n255#1:1771\n259#1:1772,2\n271#1:1774,2\n255#1:1776\n338#1:1815\n339#1:1816,2\n338#1:1818\n367#1:1819,10\n367#1:1829\n367#1:1831\n367#1:1832\n930#1:1871,3\n960#1:1874,2\n1098#1:1897,2\n1273#1:1907,3\n1288#1:1910\n1288#1:1913\n1330#1:1933,2\n1057#1:1876,2\n1128#1:1899,2\n1164#1:1901,2\n1196#1:1903,2\n1231#1:1905,2\n1289#1:1911,2\n*E\n"})
public final class BilibiliProvider extends MainAPI {

    @NotNull
    private static final String API_BASE = "https://api.bilibili.tv";

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @NotNull
    private static final String PLAYURL_API = "https://api.bilibili.tv/intl/gateway/web/playurl";

    @NotNull
    private static final String TAG = "BilibiliTVProvider";

    @NotNull
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36";

    @NotNull
    private static final String WEB_API = "https://api.bilibili.tv/intl/gateway/web/v2";

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://www.bilibili.tv";

    @NotNull
    private String name = "BilibiliTV(Requires CS Prerelease)";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";
    private final boolean hasDownloadSupport = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Anime, TvType.Movie, TvType.TvSeries});

    @NotNull
    private final Map<String, String> headers = MapsKt.mapOf(new Pair[]{TuplesKt.to("User-Agent", USER_AGENT), TuplesKt.to("Referer", getMainUrl() + '/'), TuplesKt.to("Origin", getMainUrl()), TuplesKt.to("Accept", "application/json, text/plain, */*"), TuplesKt.to("Accept-Language", "en-US,en;q=0.9")});

    @NotNull
    private final List<MainPageData> mainPage = MainAPIKt.mainPageOf(new Pair[]{TuplesKt.to("foryou", "For You"), TuplesKt.to("timeline", "Latest Updates"), TuplesKt.to("search:movie", "Movies"), TuplesKt.to("search:anime", "Anime"), TuplesKt.to("search:drama", "Drama"), TuplesKt.to("search:action", "Action"), TuplesKt.to("search:comedy", "Comedy"), TuplesKt.to("search:romance", "Romance"), TuplesKt.to("search:thriller", "Thriller"), TuplesKt.to("search:horror", "Horror"), TuplesKt.to("search:fantasy", "Fantasy"), TuplesKt.to("search:adventure", "Adventure"), TuplesKt.to("search:isekai", "Isekai"), TuplesKt.to("search:hindi", "Hindi Dubbed"), TuplesKt.to("search:tagalog", "Tagalog Dubbed")});

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/cncverse/BilibiliProvider$ContentAccessError;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "GEO_LOCKED", "PREMIUM_REQUIRED", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    private enum ContentAccessError {
        NONE,
        GEO_LOCKED,
        PREMIUM_REQUIRED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        @NotNull
        public static EnumEntries<ContentAccessError> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ContentAccessError.values().length];
            try {
                iArr[ContentAccessError.GEO_LOCKED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ContentAccessError.PREMIUM_REQUIRED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ContentAccessError.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$checkContentAccess$1, reason: invalid class name */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0}, l = {74}, m = "checkContentAccess", n = {"epId", "aid", "playurlUrl"}, nl = {77}, s = {"L$0", "L$1", "L$2"}, v = 2)
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
            return BilibiliProvider.this.checkContentAccess(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$checkGeoRestriction$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0}, l = {95}, m = "checkGeoRestriction", n = {"epId", "aid"}, nl = {-1}, s = {"L$0", "L$1"}, v = 2)
    static final class C00001 extends ContinuationImpl {
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
            return BilibiliProvider.this.checkGeoRestriction(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$extractVideoUrlsFromJson$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, l = {1298}, m = "extractVideoUrlsFromJson", n = {"json", "referer", "callback", "foundVideo", "urlPatterns", "$this$forEach$iv", "element$iv", "pattern", "$this$forEach$iv", "element$iv", "match", "url", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$extractVideoUrlsFromJson$2", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$extractVideoUrlsFromJson$2$2"}, nl = {1297}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$11", "L$12", "L$13", "I$0", "I$1", "I$2", "I$3"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
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
        int label;
        /* synthetic */ Object result;

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.extractVideoUrlsFromJson(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3}, l = {134, 163, 186, 216}, m = "getMainPage", n = {"request", "home", "popularTerms", "term", "searchUrl", "page", "request", "home", "timelineUrl", "page", "request", "home", "keyword", "searchUrl", "page", "request", "home", "searchUrl", "page"}, nl = {135, 164, 187, 217}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C00021(Continuation<? super C00021> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, l = {695, 707, 721, 722, 738, 752}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "episodeData", "epId", "seasonId", "aid", "isCasting", "foundVideo", "data", "subtitleCallback", "callback", "episodeData", "epId", "seasonId", "aid", "playerUrl", "isCasting", "foundVideo", "data", "subtitleCallback", "callback", "episodeData", "epId", "seasonId", "aid", "embedUrl", "isCasting", "foundVideo", "data", "subtitleCallback", "callback", "episodeData", "epId", "seasonId", "aid", "embedUrl", "embedContent", "isCasting", "foundVideo", "data", "subtitleCallback", "callback", "episodeData", "epId", "seasonId", "aid", "playerUrl", "isCasting", "foundVideo", "data", "subtitleCallback", "callback", "episodeData", "epId", "seasonId", "aid", "playerUrl", "isCasting", "foundVideo"}, nl = {699, 712, 722, 723, 737, 755}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "Z$0", "I$0"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int I$0;
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

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadSeason$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {309, 321, 326, 369}, m = "loadSeason", n = {"url", "seasonId", "seasonInfoUrl", "url", "seasonId", "seasonInfoUrl", "seasonResponse", "seasonJson", "season", "title", "poster", "description", "episodesUrl", "url", "seasonId", "seasonInfoUrl", "seasonResponse", "seasonJson", "season", "title", "poster", "description", "episodesUrl", "episodesResponse", "episodesJson", "firstEpId", "url", "seasonId", "seasonInfoUrl", "seasonResponse", "seasonJson", "season", "title", "poster", "description", "episodesUrl", "episodesResponse", "episodesJson", "firstEpId", "episodes", "episodeCounter", "year", "tags"}, nl = {310, 322, 327, 375}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
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

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.loadSeason(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadSubtitles$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {1327, 1335}, m = "loadSubtitles", n = {"epId", "subtitleCallback", "subtitleUrl", "epId", "subtitleCallback", "subtitleUrl", "response", "json", "$this$forEach$iv", "element$iv", "subtitle", "lang", "subUrl", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$loadSubtitles$2"}, nl = {1328, 1334}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$10", "I$0", "I$1"}, v = 2)
    static final class C00061 extends ContinuationImpl {
        int I$0;
        int I$1;
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

        C00061(Continuation<? super C00061> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.loadSubtitles(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadVideo$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3}, l = {388, 395, 409, 422}, m = "loadVideo", n = {"url", "aid", "url", "aid", "url", "aid", "videoPage", "titleMatch", "title", "coverMatch", "cover", "descMatch", "description", "url", "aid", "e"}, nl = {389, 398, 418, -1}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2"}, v = 2)
    static final class C00071 extends ContinuationImpl {
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

        C00071(Continuation<? super C00071> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.loadVideo(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 0}, l = {250}, m = "search", n = {"query", "results", "encodedQuery", "searchUrl"}, nl = {251}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C00091 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C00091(Continuation<? super C00091> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$tryExtractFromPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8}, l = {1025, 1043, 1062, 1079, 1103, 1136, 1172, 1204, 1239}, m = "tryExtractFromPage", n = {"pageUrl", "callback", "existingContent", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "pattern", "match", "stateJson", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "scriptRegex", "$this$forEach$iv", "element$iv", "scriptMatch", "scriptContent", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryExtractFromPage$2", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "playurlSSRDataRegex", "playurlSSRDataMatch", "ssrJson", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "playInfoRegex", "playInfoMatch", "playInfoJson", "playInfo", "$this$forEach$iv", "element$iv", "video", "quality", "videoUrl", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryExtractFromPage$3", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "m3u8Regex", "$this$forEach$iv", "element$iv", "match", "videoUrl", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryExtractFromPage$5", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "mp4Patterns", "pattern", "$this$forEach$iv", "element$iv", "match", "videoUrl", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryExtractFromPage$7", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "m4sRegex", "$this$forEach$iv", "element$iv", "match", "videoUrl", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryExtractFromPage$9", "pageUrl", "callback", "existingContent", "pageContent", "foundVideo", "initialStatePatterns", "cdnPatterns", "pattern", "$this$forEach$iv", "element$iv", "match", "videoUrl", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryExtractFromPage$11"}, nl = {1026, 1044, 1063, 1080, 1102, 1135, 1171, 1203, 1238}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$11", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$13", "L$14", "L$15", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$11", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$11", "L$12", "L$13", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$11", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$11", "L$12", "L$13", "I$0", "I$1"}, v = 2)
    static final class C00101 extends ContinuationImpl {
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

        C00101(Continuation<? super C00101> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.tryExtractFromPage(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$tryPlayurlApi$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider", f = "BilibiliProvider.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7}, l = {783, 798, 813, 923, 934, 943, 994, 1001}, m = "tryPlayurlApi", n = {"epId", "aid", "callback", "playurlUrl", "isEpisode", "epId", "aid", "callback", "playurlUrl", "response", "json", "isEpisode", "epId", "aid", "callback", "playurlUrl", "response", "json", "isEpisode", "epId", "aid", "callback", "playurlUrl", "response", "json", "playurl", "urlVideo", "urlAudio", "foundVideoQuality", "qualityOrder", "qualityName", "streamHeaders", "audioTracksList", "isEpisode", "foundVideo", "qualityValue", "epId", "aid", "callback", "playurlUrl", "response", "json", "playurl", "urlVideo", "urlAudio", "foundVideoQuality", "qualityOrder", "qualityName", "streamHeaders", "audioTracksList", "$this$forEachIndexed$iv", "item$iv", "audioInfo", "audioUrl", "isEpisode", "foundVideo", "qualityValue", "$i$f$forEachIndexed", "index$iv", "index", "$i$a$-forEachIndexed-BilibiliProvider$tryPlayurlApi$7", "epId", "aid", "callback", "playurlUrl", "response", "json", "playurl", "urlVideo", "urlAudio", "foundVideoQuality", "qualityOrder", "qualityName", "streamHeaders", "audioTracksList", "isEpisode", "foundVideo", "qualityValue", "epId", "aid", "callback", "playurlUrl", "response", "json", "playurl", "urlVideo", "urlAudio", "foundVideoQuality", "qualityOrder", "$this$forEach$iv", "element$iv", "videoInfo", "videoResource", "streamInfo", "qualityName", "url", "altStreamHeaders", "altAudioTracks", "isEpisode", "foundVideo", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryPlayurlApi$9", "quality", "qualityValue", "epId", "aid", "callback", "playurlUrl", "response", "json", "playurl", "urlVideo", "urlAudio", "foundVideoQuality", "qualityOrder", "$this$forEach$iv", "element$iv", "videoInfo", "videoResource", "streamInfo", "qualityName", "url", "altStreamHeaders", "altAudioTracks", "isEpisode", "foundVideo", "$i$f$forEach", "$i$a$-forEach-BilibiliProvider$tryPlayurlApi$9", "quality", "qualityValue"}, nl = {786, 797, 812, 922, 933, 942, 993, 1000}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$16", "L$17", "L$18", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$19", "L$20", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$13", "L$14", "L$15", "L$16", "L$17", "L$18", "L$19", "L$20", "I$0", "I$1", "I$2", "I$3", "I$4", "I$5"}, v = 2)
    static final class C00111 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
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

        C00111(Continuation<? super C00111> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return BilibiliProvider.this.tryPlayurlApi(null, null, null, (Continuation) this);
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

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/cncverse/BilibiliProvider$Companion;", "", "<init>", "()V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "TAG", "API_BASE", "WEB_API", "PLAYURL_API", "USER_AGENT", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return BilibiliProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            BilibiliProvider.context = context;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0190 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01a5 A[Catch: Exception -> 0x021e, TryCatch #3 {Exception -> 0x021e, blocks: (B:31:0x0126, B:37:0x0155, B:45:0x0183, B:48:0x018a, B:58:0x01bc, B:64:0x01d0, B:66:0x01d6, B:72:0x01eb, B:74:0x01f1, B:80:0x0205, B:86:0x0218, B:83:0x020c, B:85:0x0215, B:87:0x021b, B:61:0x01c6, B:57:0x01a5, B:56:0x019f, B:44:0x0178, B:36:0x014b, B:41:0x015d, B:33:0x0132, B:51:0x0190), top: B:103:0x0126, inners: #4, #6, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01d6 A[Catch: Exception -> 0x021e, TryCatch #3 {Exception -> 0x021e, blocks: (B:31:0x0126, B:37:0x0155, B:45:0x0183, B:48:0x018a, B:58:0x01bc, B:64:0x01d0, B:66:0x01d6, B:72:0x01eb, B:74:0x01f1, B:80:0x0205, B:86:0x0218, B:83:0x020c, B:85:0x0215, B:87:0x021b, B:61:0x01c6, B:57:0x01a5, B:56:0x019f, B:44:0x0178, B:36:0x014b, B:41:0x015d, B:33:0x0132, B:51:0x0190), top: B:103:0x0126, inners: #4, #6, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01eb A[Catch: Exception -> 0x021e, TryCatch #3 {Exception -> 0x021e, blocks: (B:31:0x0126, B:37:0x0155, B:45:0x0183, B:48:0x018a, B:58:0x01bc, B:64:0x01d0, B:66:0x01d6, B:72:0x01eb, B:74:0x01f1, B:80:0x0205, B:86:0x0218, B:83:0x020c, B:85:0x0215, B:87:0x021b, B:61:0x01c6, B:57:0x01a5, B:56:0x019f, B:44:0x0178, B:36:0x014b, B:41:0x015d, B:33:0x0132, B:51:0x0190), top: B:103:0x0126, inners: #4, #6, #9 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkContentAccess(java.lang.String r27, java.lang.String r28, kotlin.coroutines.Continuation<? super com.cncverse.BilibiliProvider.ContentAccessError> r29) {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.checkContentAccess(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkGeoRestriction(java.lang.String r6, java.lang.String r7, kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.cncverse.BilibiliProvider.C00001
            if (r0 == 0) goto L14
            r0 = r8
            com.cncverse.BilibiliProvider$checkGeoRestriction$1 r0 = (com.cncverse.BilibiliProvider.C00001) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.cncverse.BilibiliProvider$checkGeoRestriction$1 r0 = new com.cncverse.BilibiliProvider$checkGeoRestriction$1
            r0.<init>(r8)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            r4 = 1
            switch(r3) {
                case 0: goto L3c;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2d:
            java.lang.Object r2 = r0.L$1
            r7 = r2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r2 = r0.L$0
            r6 = r2
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r1
            goto L54
        L3c:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r3
            java.lang.Object r3 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r3
            r0.label = r4
            java.lang.Object r3 = r5.checkContentAccess(r6, r7, r0)
            if (r3 != r2) goto L54
            return r2
        L54:
            com.cncverse.BilibiliProvider$ContentAccessError r2 = com.cncverse.BilibiliProvider.ContentAccessError.GEO_LOCKED
            if (r3 != r2) goto L59
            goto L5a
        L59:
            r4 = 0
        L5a:
            java.lang.Boolean r2 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.checkGeoRestriction(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public List<MainPageData> getMainPage() {
        return this.mainPage;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x05e8: MOVE (r8 I:??[OBJECT, ARRAY]) = (r20 I:??[OBJECT, ARRAY] A[D('$continuation' kotlin.coroutines.Continuation)]), block:B:188:0x05e8 */
    /* JADX WARN: Not initialized variable reg: 27, insn: 0x05ee: MOVE (r7 I:??[OBJECT, ARRAY]) = (r27 I:??[OBJECT, ARRAY] A[D('home' java.util.List)]), block:B:188:0x05e8 */
    /* JADX WARN: Removed duplicated region for block: B:103:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x04f3 A[Catch: Exception -> 0x05cd, TryCatch #24 {Exception -> 0x05cd, blocks: (B:143:0x04cc, B:147:0x04d7, B:156:0x0508, B:158:0x0510, B:160:0x0516, B:161:0x051d, B:163:0x0523, B:165:0x0531, B:166:0x0538, B:168:0x053e, B:171:0x0555, B:174:0x0562, B:176:0x05a7, B:179:0x05b9, B:155:0x04f3, B:154:0x04ed, B:142:0x04c2, B:149:0x04dd), top: B:398:0x04c2, inners: #42 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x06e3  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x071f  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0722  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0740 A[Catch: Exception -> 0x0874, TryCatch #10 {Exception -> 0x0874, blocks: (B:221:0x0719, B:225:0x0724, B:234:0x0755, B:236:0x075d, B:238:0x0763, B:239:0x076a, B:241:0x0770, B:243:0x077e, B:244:0x0785, B:246:0x078b, B:249:0x07a5, B:251:0x07b5, B:254:0x0803, B:258:0x0848, B:261:0x0860, B:233:0x0740, B:232:0x073a, B:220:0x070f, B:227:0x072a), top: B:379:0x070f, inners: #43 }] */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0869  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0935  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x096d  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0989 A[Catch: Exception -> 0x0a6e, TryCatch #0 {Exception -> 0x0a6e, blocks: (B:300:0x0967, B:303:0x096e, B:313:0x099e, B:315:0x09a6, B:317:0x09ac, B:318:0x09b3, B:320:0x09b9, B:322:0x09c7, B:323:0x09ce, B:325:0x09d4, B:328:0x09ed, B:330:0x09f7, B:334:0x0a47, B:337:0x0a5f, B:312:0x0989, B:311:0x0983, B:299:0x095d, B:306:0x0974), top: B:364:0x095d, inners: #20, #41 }] */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0af7  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x049b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x021f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x06e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0939 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:432:0x072a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:434:0x04dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0974 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0261 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0277 A[Catch: Exception -> 0x03ac, TryCatch #1 {Exception -> 0x03ac, blocks: (B:61:0x0250, B:65:0x025b, B:74:0x028c, B:76:0x0294, B:78:0x029a, B:79:0x02a1, B:81:0x02a7, B:83:0x02b5, B:84:0x02bc, B:86:0x02c2, B:89:0x02dc, B:91:0x02ec, B:94:0x0336, B:98:0x037f, B:101:0x0397, B:73:0x0277, B:72:0x0271, B:60:0x0246, B:67:0x0261), top: B:366:0x0246, inners: #32, #40 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v27 */
    /* JADX WARN: Type inference failed for: r10v29 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 10 */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r44, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r45, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r46) {
        /*
            Method dump skipped, instruction units count: 2832
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$0$0(BiliSearchItem $item, BilibiliProvider this$0, AnimeSearchResponse $this$newAnimeSearchResponse) {
        String cover = $item.getCover();
        $this$newAnimeSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$0$0$1(BiliSearchItem $item, BilibiliProvider this$0, MovieSearchResponse $this$newMovieSearchResponse) {
        String cover = $item.getCover();
        $this$newMovieSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$1$0$0(BiliTimelineCard $card, BilibiliProvider this$0, AnimeSearchResponse $this$newAnimeSearchResponse) {
        String cover = $card.getCover();
        $this$newAnimeSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$2$0$0(BiliSearchItem $item, BilibiliProvider this$0, AnimeSearchResponse $this$newAnimeSearchResponse) {
        String cover = $item.getCover();
        $this$newAnimeSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$2$0$1(BiliSearchItem $item, BilibiliProvider this$0, MovieSearchResponse $this$newMovieSearchResponse) {
        String cover = $item.getCover();
        $this$newMovieSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$3$0$0(BiliSearchItem $item, BilibiliProvider this$0, AnimeSearchResponse $this$newAnimeSearchResponse) {
        String cover = $item.getCover();
        $this$newAnimeSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x013f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0174 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0189 A[Catch: Exception -> 0x02f6, TryCatch #5 {Exception -> 0x02f6, blocks: (B:24:0x00ec, B:30:0x0137, B:38:0x0166, B:42:0x016e, B:51:0x019e, B:53:0x01a6, B:55:0x01ac, B:56:0x01b3, B:58:0x01b9, B:60:0x01cd, B:62:0x01d3, B:63:0x01da, B:65:0x01e0, B:68:0x01f7, B:71:0x0204, B:75:0x0257, B:77:0x0263, B:79:0x0269, B:80:0x0270, B:82:0x0276, B:85:0x028d, B:88:0x029a, B:50:0x0189, B:49:0x0183, B:37:0x015b, B:29:0x012d, B:34:0x013f, B:26:0x0114, B:44:0x0174), top: B:110:0x00ec, inners: #4, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02f1  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r33, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r34) {
        /*
            Method dump skipped, instruction units count: 816
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$0$0(BiliSearchItem $item, BilibiliProvider this$0, AnimeSearchResponse $this$newAnimeSearchResponse) {
        String cover = $item.getCover();
        $this$newAnimeSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$0$1$0(BiliSearchItem $item, BilibiliProvider this$0, MovieSearchResponse $this$newMovieSearchResponse) {
        String cover = $item.getCover();
        $this$newMovieSearchResponse.setPosterUrl(cover != null ? this$0.ensureHttps(cover) : null);
        return Unit.INSTANCE;
    }

    @Nullable
    public Object load(@NotNull String url, @NotNull Continuation<? super LoadResponse> continuation) {
        Log.d(TAG, "Loading: " + url);
        if (StringsKt.contains$default(url, "/play/", false, 2, (Object) null)) {
            return loadSeason(url, continuation);
        }
        if (StringsKt.contains$default(url, "/video/", false, 2, (Object) null)) {
            return loadVideo(url, continuation);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x00ea: MOVE (r3 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY] A[D('seasonId' java.lang.String)]), block:B:19:0x00e8 */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x00f2: MOVE (r3 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY] A[D('seasonId' java.lang.String)]), block:B:21:0x00f1 */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0485 A[Catch: Exception -> 0x0522, ErrorLoadingException -> 0x0774, TRY_ENTER, TryCatch #4 {ErrorLoadingException -> 0x0774, blocks: (B:178:0x04ec, B:179:0x04f6, B:180:0x04f9, B:186:0x051e, B:187:0x0521, B:182:0x050e, B:183:0x0515, B:184:0x0516, B:185:0x051d, B:117:0x03b9, B:123:0x03eb, B:139:0x0433, B:142:0x043a, B:161:0x047c, B:163:0x0485, B:165:0x048b, B:167:0x0493, B:169:0x0499, B:171:0x04a1, B:174:0x04a9, B:160:0x0462, B:157:0x0459, B:138:0x0429, B:122:0x03e1), top: B:299:0x03b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x04a9 A[Catch: Exception -> 0x0522, ErrorLoadingException -> 0x0774, TryCatch #4 {ErrorLoadingException -> 0x0774, blocks: (B:178:0x04ec, B:179:0x04f6, B:180:0x04f9, B:186:0x051e, B:187:0x0521, B:182:0x050e, B:183:0x0515, B:184:0x0516, B:185:0x051d, B:117:0x03b9, B:123:0x03eb, B:139:0x0433, B:142:0x043a, B:161:0x047c, B:163:0x0485, B:165:0x048b, B:167:0x0493, B:169:0x0499, B:171:0x04a1, B:174:0x04a9, B:160:0x0462, B:157:0x0459, B:138:0x0429, B:122:0x03e1), top: B:299:0x03b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x04f9 A[Catch: Exception -> 0x0522, ErrorLoadingException -> 0x0774, TryCatch #4 {ErrorLoadingException -> 0x0774, blocks: (B:178:0x04ec, B:179:0x04f6, B:180:0x04f9, B:186:0x051e, B:187:0x0521, B:182:0x050e, B:183:0x0515, B:184:0x0516, B:185:0x051d, B:117:0x03b9, B:123:0x03eb, B:139:0x0433, B:142:0x043a, B:161:0x047c, B:163:0x0485, B:165:0x048b, B:167:0x0493, B:169:0x0499, B:171:0x04a1, B:174:0x04a9, B:160:0x0462, B:157:0x0459, B:138:0x0429, B:122:0x03e1), top: B:299:0x03b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x050e A[Catch: Exception -> 0x0522, ErrorLoadingException -> 0x0774, TryCatch #4 {ErrorLoadingException -> 0x0774, blocks: (B:178:0x04ec, B:179:0x04f6, B:180:0x04f9, B:186:0x051e, B:187:0x0521, B:182:0x050e, B:183:0x0515, B:184:0x0516, B:185:0x051d, B:117:0x03b9, B:123:0x03eb, B:139:0x0433, B:142:0x043a, B:161:0x047c, B:163:0x0485, B:165:0x048b, B:167:0x0493, B:169:0x0499, B:171:0x04a1, B:174:0x04a9, B:160:0x0462, B:157:0x0459, B:138:0x0429, B:122:0x03e1), top: B:299:0x03b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0516 A[Catch: Exception -> 0x0522, ErrorLoadingException -> 0x0774, TryCatch #4 {ErrorLoadingException -> 0x0774, blocks: (B:178:0x04ec, B:179:0x04f6, B:180:0x04f9, B:186:0x051e, B:187:0x0521, B:182:0x050e, B:183:0x0515, B:184:0x0516, B:185:0x051d, B:117:0x03b9, B:123:0x03eb, B:139:0x0433, B:142:0x043a, B:161:0x047c, B:163:0x0485, B:165:0x048b, B:167:0x0493, B:169:0x0499, B:171:0x04a1, B:174:0x04a9, B:160:0x0462, B:157:0x0459, B:138:0x0429, B:122:0x03e1), top: B:299:0x03b9 }] */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x056a A[Catch: Exception -> 0x0760, ErrorLoadingException -> 0x0769, TryCatch #8 {ErrorLoadingException -> 0x0769, blocks: (B:191:0x0537, B:193:0x0552, B:195:0x056a, B:197:0x0570, B:198:0x057a, B:200:0x0580, B:202:0x0594, B:203:0x059e, B:205:0x05a4, B:208:0x05bf, B:210:0x05c5, B:217:0x05e5, B:219:0x062b, B:222:0x0648, B:224:0x0657, B:226:0x065e, B:228:0x066a, B:230:0x0673, B:232:0x0679, B:233:0x068c, B:235:0x0692, B:237:0x06a6, B:239:0x06b1, B:241:0x06bb, B:243:0x06c2, B:245:0x073d), top: B:303:0x0537 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x05de  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x05e5 A[Catch: Exception -> 0x0760, ErrorLoadingException -> 0x0769, TryCatch #8 {ErrorLoadingException -> 0x0769, blocks: (B:191:0x0537, B:193:0x0552, B:195:0x056a, B:197:0x0570, B:198:0x057a, B:200:0x0580, B:202:0x0594, B:203:0x059e, B:205:0x05a4, B:208:0x05bf, B:210:0x05c5, B:217:0x05e5, B:219:0x062b, B:222:0x0648, B:224:0x0657, B:226:0x065e, B:228:0x066a, B:230:0x0673, B:232:0x0679, B:233:0x068c, B:235:0x0692, B:237:0x06a6, B:239:0x06b1, B:241:0x06bb, B:243:0x06c2, B:245:0x073d), top: B:303:0x0537 }] */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x065e A[Catch: Exception -> 0x0760, ErrorLoadingException -> 0x0769, TryCatch #8 {ErrorLoadingException -> 0x0769, blocks: (B:191:0x0537, B:193:0x0552, B:195:0x056a, B:197:0x0570, B:198:0x057a, B:200:0x0580, B:202:0x0594, B:203:0x059e, B:205:0x05a4, B:208:0x05bf, B:210:0x05c5, B:217:0x05e5, B:219:0x062b, B:222:0x0648, B:224:0x0657, B:226:0x065e, B:228:0x066a, B:230:0x0673, B:232:0x0679, B:233:0x068c, B:235:0x0692, B:237:0x06a6, B:239:0x06b1, B:241:0x06bb, B:243:0x06c2, B:245:0x073d), top: B:303:0x0537 }] */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0671  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x078d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0273 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:323:0x02ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0440 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02eb A[Catch: Exception -> 0x0796, ErrorLoadingException -> 0x07a9, TryCatch #6 {ErrorLoadingException -> 0x07a9, blocks: (B:53:0x0221, B:55:0x0241, B:61:0x026a, B:74:0x029e, B:77:0x02a5, B:91:0x02e3, B:93:0x02eb, B:96:0x02f9, B:100:0x0302, B:110:0x031b, B:104:0x030e, B:90:0x02cc, B:73:0x0293, B:60:0x0260), top: B:301:0x0221 }] */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadSeason(java.lang.String r43, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r44) {
        /*
            Method dump skipped, instruction units count: 2084
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.loadSeason(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit loadSeason$lambda$0$0$0(com.cncverse.BilibiliProvider.BiliEpisode r3, kotlin.jvm.internal.Ref.IntRef r4, com.cncverse.BilibiliProvider r5, com.lagradost.cloudstream3.Episode r6) {
        /*
            java.lang.String r0 = r3.getLongTitleDisplay()
            if (r0 != 0) goto L27
            java.lang.String r0 = r3.getTitleDisplay()
            if (r0 != 0) goto L27
            java.lang.String r0 = r3.getShortTitleDisplay()
            if (r0 != 0) goto L27
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Episode "
            java.lang.StringBuilder r0 = r0.append(r1)
            int r1 = r4.element
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
        L27:
            r6.setName(r0)
            java.lang.String r0 = r3.getShortTitleDisplay()
            if (r0 == 0) goto L47
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            kotlin.text.Regex r1 = new kotlin.text.Regex
            java.lang.String r2 = "[^0-9]"
            r1.<init>(r2)
            java.lang.String r2 = ""
            java.lang.String r0 = r1.replace(r0, r2)
            if (r0 == 0) goto L47
            java.lang.Integer r0 = kotlin.text.StringsKt.toIntOrNull(r0)
            if (r0 != 0) goto L4d
        L47:
            int r0 = r4.element
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L4d:
            r6.setEpisode(r0)
            java.lang.String r0 = r3.getCover()
            if (r0 == 0) goto L5b
            java.lang.String r0 = r5.ensureHttps(r0)
            goto L5c
        L5b:
            r0 = 0
        L5c:
            r6.setPosterUrl(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.loadSeason$lambda$0$0$0(com.cncverse.BilibiliProvider$BiliEpisode, kotlin.jvm.internal.Ref$IntRef, com.cncverse.BilibiliProvider, com.lagradost.cloudstream3.Episode):kotlin.Unit");
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadSeason$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$loadSeason$3", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00053 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<String> $description;
        final /* synthetic */ String $poster;
        final /* synthetic */ List<String> $tags;
        final /* synthetic */ Integer $year;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00053(String str, Ref.ObjectRef<String> objectRef, Integer num, List<String> list, Continuation<? super C00053> continuation) {
            super(2, continuation);
            this.$poster = str;
            this.$description = objectRef;
            this.$year = num;
            this.$tags = list;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00053 = new C00053(this.$poster, this.$description, this.$year, this.$tags, continuation);
            c00053.L$0 = obj;
            return c00053;
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
                    $this$newTvSeriesLoadResponse.setPlot((String) this.$description.element);
                    $this$newTvSeriesLoadResponse.setYear(this.$year);
                    $this$newTvSeriesLoadResponse.setTags(this.$tags);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 24, insn: 0x02d0: MOVE (r15 I:??[OBJECT, ARRAY]) = (r24 I:??[OBJECT, ARRAY] A[D('aid' java.lang.String)]), block:B:132:0x02cf */
    /* JADX WARN: Not initialized variable reg: 24, insn: 0x02d5: MOVE (r15 I:??[OBJECT, ARRAY]) = (r24 I:??[OBJECT, ARRAY] A[D('aid' java.lang.String)]), block:B:134:0x02d4 */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02be A[Catch: Exception -> 0x02ce, ErrorLoadingException -> 0x02d3, TryCatch #20 {Exception -> 0x02ce, ErrorLoadingException -> 0x02d3, blocks: (B:43:0x00fc, B:135:0x02d9, B:136:0x02dc, B:127:0x02b6, B:128:0x02bd, B:129:0x02be, B:130:0x02cd), top: B:170:0x00f1 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x037c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0166 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01de  */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadVideo(java.lang.String r29, kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r30) {
        /*
            Method dump skipped, instruction units count: 932
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.loadVideo(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadVideo$2, reason: invalid class name */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$loadVideo$2", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $cover;
        final /* synthetic */ String $description;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$cover = str;
            this.$description = str2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$cover, this.$description, continuation);
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
                    $this$newMovieLoadResponse.setPosterUrl(this.$cover);
                    $this$newMovieLoadResponse.setPlot(this.$description);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadVideo$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$loadVideo$3", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00083 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00083(Continuation<? super C00083> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00083 = new C00083(continuation);
            c00083.L$0 = obj;
            return c00083;
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
                    $this$newMovieLoadResponse.setPlot("Bilibili.tv video");
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x016a: MOVE (r12 I:??[OBJECT, ARRAY]) = (r11 I:??[OBJECT, ARRAY] A[D('epId' java.lang.String)]), block:B:29:0x0167 */
    /* JADX WARN: Not initialized variable reg: 21, insn: 0x0178: MOVE (r13 I:??[OBJECT, ARRAY]) = (r21 I:??[OBJECT, ARRAY] A[D('subtitleCallback' kotlin.jvm.functions.Function1)]), block:B:29:0x0167 */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x0174: MOVE (r10 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY] A[D('embedUrl' java.lang.String)]), block:B:29:0x0167 */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x0167: MOVE (r8 I:??[OBJECT, ARRAY]) = (r7 I:??[OBJECT, ARRAY] A[D('aid' java.lang.String)]), block:B:29:0x0167 */
    /* JADX WARN: Removed duplicated region for block: B:114:0x039e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x05b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x069f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x06c5  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x06c7 A[Catch: Exception -> 0x06e1, TRY_LEAVE, TryCatch #17 {Exception -> 0x06e1, blocks: (B:197:0x06a1, B:199:0x06c7), top: B:281:0x069d }] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x06e6  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x06eb  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0783 A[Catch: Exception -> 0x0764, TRY_LEAVE, TryCatch #16 {Exception -> 0x0764, blocks: (B:216:0x075d, B:228:0x0783), top: B:278:0x075d }] */
    /* JADX WARN: Removed duplicated region for block: B:233:0x07d2  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x04a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:285:0x04f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x03bc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v122 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v136 */
    /* JADX WARN: Type inference failed for: r0v143 */
    /* JADX WARN: Type inference failed for: r0v144 */
    /* JADX WARN: Type inference failed for: r0v145 */
    /* JADX WARN: Type inference failed for: r0v20, types: [int] */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v67, types: [int] */
    /* JADX WARN: Type inference failed for: r0v72 */
    /* JADX WARN: Type inference failed for: r0v98 */
    /* JADX WARN: Type inference failed for: r3v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v55 */
    /* JADX WARN: Type inference failed for: r3v56 */
    /* JADX WARN: Type inference failed for: r3v57 */
    /* JADX WARN: Type inference failed for: r3v58 */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v43, types: [int] */
    /* JADX WARN: Type inference failed for: r4v66 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v37 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v40 */
    /* JADX WARN: Type inference failed for: r9v42 */
    /* JADX WARN: Type inference failed for: r9v43 */
    /* JADX WARN: Type inference failed for: r9v47, types: [boolean] */
    /* JADX WARN: Type inference failed for: r9v48 */
    /* JADX WARN: Type inference failed for: r9v49 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v50 */
    /* JADX WARN: Type inference failed for: r9v51 */
    /* JADX WARN: Type inference failed for: r9v52 */
    /* JADX WARN: Type inference failed for: r9v53 */
    /* JADX WARN: Type inference failed for: r9v7 */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r36, boolean r37, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r38, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r39, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r40) {
        /*
            Method dump skipped, instruction units count: 2124
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$loadLinks$3, reason: invalid class name */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$loadLinks$3", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = BilibiliProvider.this.new AnonymousClass3(continuation);
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
                    $this$newExtractorLink.setQuality(Qualities.Unknown.getValue());
                    $this$newExtractorLink.setReferer(BilibiliProvider.this.getMainUrl());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(14:42|(14:584|44|(1:46)|50|(1:(2:558|57)(4:508|548|509|510))(1:54)|58|59|524|60|61|546|62|63|(1:65)(22:66|566|67|68|560|69|73|(1:75)(5:554|76|77|552|78)|85|(1:87)|88|89|(6:595|91|98|(4:158|159|(1:161)(1:162)|(2:164|165)(13:166|167|585|168|169|(7:171|556|172|(2:173|(4:175|(2:176|(11:178|(1:183)(1:182)|184|(1:190)|191|192|193|532|194|(1:603)(3:196|(1:198)(1:199)|(2:601|201)(1:604))|202)(2:602|203))|204|(2:599|206)(1:207))(2:600|208))|209|(2:211|(2:212|(1:606)(5:214|(1:220)|221|(2:223|608)(2:224|607)|(4:605|226|(1:231)(1:230)|232)(1:609))))|233)(1:237)|578|238|(4:240|241|(5:243|(1:245)(1:246)|247|(1:249)(1:250)|251)|252)|253|254|(1:260)(1:259)|(8:520|262|SW:263|271|SW:272|282|(1:288)(1:287)|(2:290|(1:292)(5:293|294|295|299|(8:593|301|302|526|303|(12:305|306|(2:518|308)|309|(1:313)|314|(1:316)(1:317)|(2:319|(3:321|322|(1:324)(4:325|536|326|327))(2:330|332))(2:331|598)|333|526|303|(12:334|335|336|570|345|346|591|347|348|528|349|(1:351)(16:352|550|353|354|572|355|(3:357|538|358)(1:363)|562|364|365|589|366|367|522|386|(7:388|389|534|390|(14:392|(13:540|394|(0)|402|(1:408)(1:406)|409|410|(1:412)(1:413)|(4:415|544|416|(2:SW:418|SW:427)(2:473|477))(2:476|597)|478|534|390|(7:479|480|481|(1:488)(1:489)|530|490|491)(0))|401|402|(1:404)|408|409|410|(0)(0)|(0)(0)|478|534|390|(0)(0))(0)|482|483)(5:486|(0)(0)|530|490|491)))(0))(0)|337|338)(10:343|570|345|346|591|347|348|528|349|(0)(0))))(3:298|299|(0)(0)))(4:385|522|386|(0)(0))))(6:100|580|104|(3:147|148|(1:150)(1:151))(7:106|110|(9:542|112|113|114|115|(1:117)|(3:123|(1:128)(1:127)|(1:130)(2:131|(4:136|137|138|(1:140)(3:141|142|143))(3:144|145|146)))(0)|148|(0)(0))(1:120)|121|(0)(0)|148|(0)(0))|152|153)|513|514)|97|98|(7:101|158|159|(0)(0)|(0)(0)|513|514)|100|580|104|(0)(4:107|147|148|(0)(0))|152|153))|49|50|(1:52)(1:(0)(0))|58|59|524|60|61|546|62|63|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(22:66|566|67|68|560|69|73|(1:75)(5:554|76|77|552|78)|85|(1:87)|88|89|(6:595|91|98|(4:158|159|(1:161)(1:162)|(2:164|165)(13:166|167|585|168|169|(7:171|556|172|(2:173|(4:175|(2:176|(11:178|(1:183)(1:182)|184|(1:190)|191|192|193|532|194|(1:603)(3:196|(1:198)(1:199)|(2:601|201)(1:604))|202)(2:602|203))|204|(2:599|206)(1:207))(2:600|208))|209|(2:211|(2:212|(1:606)(5:214|(1:220)|221|(2:223|608)(2:224|607)|(4:605|226|(1:231)(1:230)|232)(1:609))))|233)(1:237)|578|238|(4:240|241|(5:243|(1:245)(1:246)|247|(1:249)(1:250)|251)|252)|253|254|(1:260)(1:259)|(8:520|262|SW:263|271|SW:272|282|(1:288)(1:287)|(2:290|(1:292)(5:293|294|295|299|(8:593|301|302|526|303|(12:305|306|(2:518|308)|309|(1:313)|314|(1:316)(1:317)|(2:319|(3:321|322|(1:324)(4:325|536|326|327))(2:330|332))(2:331|598)|333|526|303|(12:334|335|336|570|345|346|591|347|348|528|349|(1:351)(16:352|550|353|354|572|355|(3:357|538|358)(1:363)|562|364|365|589|366|367|522|386|(7:388|389|534|390|(14:392|(13:540|394|(0)|402|(1:408)(1:406)|409|410|(1:412)(1:413)|(4:415|544|416|(2:SW:418|SW:427)(2:473|477))(2:476|597)|478|534|390|(7:479|480|481|(1:488)(1:489)|530|490|491)(0))|401|402|(1:404)|408|409|410|(0)(0)|(0)(0)|478|534|390|(0)(0))(0)|482|483)(5:486|(0)(0)|530|490|491)))(0))(0)|337|338)(10:343|570|345|346|591|347|348|528|349|(0)(0))))(3:298|299|(0)(0)))(4:385|522|386|(0)(0))))(6:100|580|104|(3:147|148|(1:150)(1:151))(7:106|110|(9:542|112|113|114|115|(1:117)|(3:123|(1:128)(1:127)|(1:130)(2:131|(4:136|137|138|(1:140)(3:141|142|143))(3:144|145|146)))(0)|148|(0)(0))(1:120)|121|(0)(0)|148|(0)(0))|152|153)|513|514)|97|98|(7:101|158|159|(0)(0)|(0)(0)|513|514)|100|580|104|(0)(4:107|147|148|(0)(0))|152|153) */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x07bd, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x07be, code lost:
    
        r28 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:502:0x13a5, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:503:0x13a6, code lost:
    
        r28 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:504:0x13b7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:505:0x13b8, code lost:
    
        r28 = com.cncverse.BilibiliProvider.TAG;
     */
    /* JADX WARN: Path cross not found for [B:404:0x0f67, B:408:0x0f72], limit reached: 590 */
    /* JADX WARN: Path cross not found for [B:442:0x106e, B:446:0x1077], limit reached: 590 */
    /* JADX WARN: Path cross not found for [B:446:0x1077, B:442:0x106e], limit reached: 590 */
    /* JADX WARN: Path cross not found for [B:540:0x0f46, B:401:0x0f61], limit reached: 590 */
    /* JADX WARN: Path cross not found for [B:584:0x0467, B:49:0x0488], limit reached: 590 */
    /* JADX WARN: Path cross not found for [B:595:0x061b, B:97:0x0630], limit reached: 590 */
    /* JADX WARN: Path cross not found for [B:97:0x0630, B:595:0x061b], limit reached: 590 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0650 A[Catch: Exception -> 0x1397, TRY_LEAVE, TryCatch #28 {Exception -> 0x1397, blocks: (B:67:0x0587, B:73:0x05d2, B:85:0x060e, B:88:0x0615, B:98:0x0645, B:101:0x0650, B:159:0x07d3, B:166:0x07eb, B:169:0x081f, B:97:0x0630, B:84:0x0604, B:72:0x05c8, B:69:0x05af), top: B:566:0x0587, inners: #25 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0686 A[Catch: Exception -> 0x07bd, TryCatch #36 {Exception -> 0x07bd, blocks: (B:104:0x0657, B:110:0x0690, B:123:0x06bd, B:131:0x06de, B:134:0x06e9, B:136:0x06f3, B:107:0x0686), top: B:580:0x0657 }] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x06bd A[Catch: Exception -> 0x07bd, TRY_ENTER, TRY_LEAVE, TryCatch #36 {Exception -> 0x07bd, blocks: (B:104:0x0657, B:110:0x0690, B:123:0x06bd, B:131:0x06de, B:134:0x06e9, B:136:0x06f3, B:107:0x0686), top: B:580:0x0657 }] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0754  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x079f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x07d9 A[Catch: Exception -> 0x07ae, TRY_ENTER, TryCatch #1 {Exception -> 0x07ae, blocks: (B:142:0x0743, B:152:0x07a6, B:114:0x069f, B:125:0x06c3, B:148:0x075a, B:145:0x074f, B:138:0x06ff, B:161:0x07d9, B:164:0x07e1, B:180:0x087b, B:182:0x0881, B:186:0x088c, B:188:0x0892, B:96:0x062a, B:91:0x061b), top: B:517:0x0044, inners: #41, #45 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x07de  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x07e1 A[Catch: Exception -> 0x07ae, TRY_LEAVE, TryCatch #1 {Exception -> 0x07ae, blocks: (B:142:0x0743, B:152:0x07a6, B:114:0x069f, B:125:0x06c3, B:148:0x075a, B:145:0x074f, B:138:0x06ff, B:161:0x07d9, B:164:0x07e1, B:180:0x087b, B:182:0x0881, B:186:0x088c, B:188:0x0892, B:96:0x062a, B:91:0x061b), top: B:517:0x0044, inners: #41, #45 }] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x07eb A[Catch: Exception -> 0x1397, TRY_ENTER, TRY_LEAVE, TryCatch #28 {Exception -> 0x1397, blocks: (B:67:0x0587, B:73:0x05d2, B:85:0x060e, B:88:0x0615, B:98:0x0645, B:101:0x0650, B:159:0x07d3, B:166:0x07eb, B:169:0x081f, B:97:0x0630, B:84:0x0604, B:72:0x05c8, B:69:0x05af), top: B:566:0x0587, inners: #25 }] */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0bb2 A[Catch: Exception -> 0x0d37, TRY_LEAVE, TryCatch #7 {Exception -> 0x0d37, blocks: (B:303:0x0bac, B:305:0x0bb2), top: B:526:0x0bac }] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0d17  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0d50  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0e02 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0e03  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0e51  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0e66  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0f15 A[Catch: Exception -> 0x1378, TRY_LEAVE, TryCatch #5 {Exception -> 0x1378, blocks: (B:386:0x0f0f, B:388:0x0f15), top: B:522:0x0f0f }] */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0f31 A[Catch: Exception -> 0x135e, TRY_LEAVE, TryCatch #11 {Exception -> 0x135e, blocks: (B:390:0x0f2b, B:392:0x0f31, B:410:0x0f76), top: B:534:0x0f2b }] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0f80  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0f83  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0f87  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x106e A[Catch: Exception -> 0x1357, TryCatch #29 {Exception -> 0x1357, blocks: (B:440:0x1025, B:442:0x106e, B:448:0x107b, B:480:0x1345), top: B:568:0x1025 }] */
    /* JADX WARN: Removed duplicated region for block: B:448:0x107b A[Catch: Exception -> 0x1357, TRY_LEAVE, TryCatch #29 {Exception -> 0x1357, blocks: (B:440:0x1025, B:442:0x106e, B:448:0x107b, B:480:0x1345), top: B:568:0x1025 }] */
    /* JADX WARN: Removed duplicated region for block: B:457:0x1161  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x1271 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:464:0x1272  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x12f0  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x1337  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x1369  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x136d  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x136f  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x13d9  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x05dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:558:0x04c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:593:0x0b8e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:595:0x061b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x057e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0614  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:325:0x0c86 -> B:536:0x0cb2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:464:0x1272 -> B:515:0x1291). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryPlayurlApi(java.lang.String r56, java.lang.String r57, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r58, kotlin.coroutines.Continuation<? super java.lang.Boolean> r59) {
        /*
            Method dump skipped, instruction units count: 5254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.tryPlayurlApi(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$tryPlayurlApi$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$tryPlayurlApi$2", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00122 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00122(Continuation<? super C00122> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00122 = new C00122(continuation);
            c00122.L$0 = obj;
            return c00122;
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
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$tryPlayurlApi$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$tryPlayurlApi$3", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class C00133 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C00133(Continuation<? super C00133> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> c00133 = new C00133(continuation);
            c00133.L$0 = obj;
            return c00133;
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
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$tryPlayurlApi$6, reason: invalid class name */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/AudioFile;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$tryPlayurlApi$6", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<AudioFile, Continuation<? super Unit>, Object> {
        final /* synthetic */ Map<String, String> $streamHeaders;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(Map<String, String> map, Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
            this.$streamHeaders = map;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass6 = new AnonymousClass6(this.$streamHeaders, continuation);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        public final Object invoke(AudioFile audioFile, Continuation<? super Unit> continuation) {
            return create(audioFile, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            AudioFile $this$newAudioFile = (AudioFile) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newAudioFile.setHeaders(this.$streamHeaders);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.BilibiliProvider$tryPlayurlApi$8, reason: invalid class name */
    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.BilibiliProvider$tryPlayurlApi$8", f = "BilibiliProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass8 extends SuspendLambda implements Function2<ExtractorLink, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<AudioFile> $audioTracksList;
        final /* synthetic */ int $qualityValue;
        final /* synthetic */ Map<String, String> $streamHeaders;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ BilibiliProvider this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass8(int i, BilibiliProvider bilibiliProvider, Map<String, String> map, List<AudioFile> list, Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
            this.$qualityValue = i;
            this.this$0 = bilibiliProvider;
            this.$streamHeaders = map;
            this.$audioTracksList = list;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass8 = new AnonymousClass8(this.$qualityValue, this.this$0, this.$streamHeaders, this.$audioTracksList, continuation);
            anonymousClass8.L$0 = obj;
            return anonymousClass8;
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
                    $this$newExtractorLink.setQuality(this.$qualityValue);
                    $this$newExtractorLink.setReferer(this.this$0.getMainUrl());
                    $this$newExtractorLink.setHeaders(this.$streamHeaders);
                    $this$newExtractorLink.setAudioTracks(this.$audioTracksList);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(10:56|(6:577|57|527|66|67|615)|(6:70|71|537|72|73|(6:593|75|76|553|77|(1:79)(4:80|549|81|(7:83|84|531|100|(24:102|103|613|(11:106|107|535|108|109|475|110|(2:119|130)|120|121|(1:123)(6:124|513|125|(2:127|128)(2:129|607)|613|(2:104|555)))|608|138|491|142|(3:144|145|(6:589|147|148|597|149|(1:151)(18:152|601|153|154|507|172|(6:174|175|579|176|177|(26:557|179|180|561|181|185|(1:187)(11:519|188|189|511|190|191|501|192|193|497|194)|205|(1:207)|208|209|(15:605|211|221|(2:225|(20:485|227|228|493|229|(23:231|232|563|233|(3:483|235|(5:237|260|493|229|(13:274|275|276|297|(5:299|300|472|301|(5:303|304|581|305|(7:307|551|308|309|545|310|(1:312)(8:313|565|314|315|323|472|301|(1:326)(0)))(5:322|323|472|301|(0)(0)))(0))|329|(4:331|499|332|(8:334|335|585|336|337|595|338|(11:340|341|477|342|343|495|344|(2:525|346)|350|351|(5:353|354|547|355|(1:357)(8:358|567|359|360|364|595|338|(3:369|370|(1:372)(4:373|499|332|(1:380)(0)))(0)))(5:363|364|595|338|(0)(0)))(0))(0))|383|(5:385|386|503|387|(5:389|390|575|391|(7:393|569|394|395|559|396|(1:398)(8:399|517|400|401|409|503|387|(1:412)(0)))(5:408|409|503|387|(0)(0)))(0))|415|(3:417|418|(7:420|421|422|423|571|424|(5:426|427|541|428|(5:430|431|603|432|(1:434)(8:435|587|436|437|441|571|424|(3:444|445|(1:447)(3:448|418|(1:453)(0)))(0)))(5:440|441|571|424|(0)(0)))(0))(0))|454|455)(0)))|240|241|(2:243|244)(1:245)|523|246|247|543|248|249|487|250|251|470|252|253|583|254|(1:256)(8:257|509|258|259|260|493|229|(0)(0)))(0)|277|278|573|289|297|(0)|329|(0)|383|(0)|415|(0)|454|455))|283|297|(0)|329|(0)|383|(0)|415|(0)|454|455)|220|221|(3:223|225|(0))|283|297|(0)|329|(0)|383|(0)|415|(0)|454|455)(1:292))(1:295)|296|297|(0)|329|(0)|383|(0)|415|(0)|454|455))(1:169))(1:170)|171|507|172|(0)(0)|296|297|(0)|329|(0)|383|(0)|415|(0)|454|455)(19:141|491|142|(0)(0)|171|507|172|(0)(0)|296|297|(0)|329|(0)|383|(0)|415|(0)|454|455)|466|467)(3:85|615|(2:68|539))))(1:96))|614|99|531|100|(0)(0)|466|467) */
    /* JADX WARN: Can't wrap try/catch for region: R(6:(1:593)|75|76|553|77|(1:79)(4:80|549|81|(7:83|84|531|100|(24:102|103|613|(11:106|107|535|108|109|475|110|(2:119|130)|120|121|(1:123)(6:124|513|125|(2:127|128)(2:129|607)|613|(2:104|555)))|608|138|491|142|(3:144|145|(6:589|147|148|597|149|(1:151)(18:152|601|153|154|507|172|(6:174|175|579|176|177|(26:557|179|180|561|181|185|(1:187)(11:519|188|189|511|190|191|501|192|193|497|194)|205|(1:207)|208|209|(15:605|211|221|(2:225|(20:485|227|228|493|229|(23:231|232|563|233|(3:483|235|(5:237|260|493|229|(13:274|275|276|297|(5:299|300|472|301|(5:303|304|581|305|(7:307|551|308|309|545|310|(1:312)(8:313|565|314|315|323|472|301|(1:326)(0)))(5:322|323|472|301|(0)(0)))(0))|329|(4:331|499|332|(8:334|335|585|336|337|595|338|(11:340|341|477|342|343|495|344|(2:525|346)|350|351|(5:353|354|547|355|(1:357)(8:358|567|359|360|364|595|338|(3:369|370|(1:372)(4:373|499|332|(1:380)(0)))(0)))(5:363|364|595|338|(0)(0)))(0))(0))|383|(5:385|386|503|387|(5:389|390|575|391|(7:393|569|394|395|559|396|(1:398)(8:399|517|400|401|409|503|387|(1:412)(0)))(5:408|409|503|387|(0)(0)))(0))|415|(3:417|418|(7:420|421|422|423|571|424|(5:426|427|541|428|(5:430|431|603|432|(1:434)(8:435|587|436|437|441|571|424|(3:444|445|(1:447)(3:448|418|(1:453)(0)))(0)))(5:440|441|571|424|(0)(0)))(0))(0))|454|455)(0)))|240|241|(2:243|244)(1:245)|523|246|247|543|248|249|487|250|251|470|252|253|583|254|(1:256)(8:257|509|258|259|260|493|229|(0)(0)))(0)|277|278|573|289|297|(0)|329|(0)|383|(0)|415|(0)|454|455))|283|297|(0)|329|(0)|383|(0)|415|(0)|454|455)|220|221|(3:223|225|(0))|283|297|(0)|329|(0)|383|(0)|415|(0)|454|455)(1:292))(1:295)|296|297|(0)|329|(0)|383|(0)|415|(0)|454|455))(1:169))(1:170)|171|507|172|(0)(0)|296|297|(0)|329|(0)|383|(0)|415|(0)|454|455)(19:141|491|142|(0)(0)|171|507|172|(0)(0)|296|297|(0)|329|(0)|383|(0)|415|(0)|454|455)|466|467)(3:85|615|(2:68|539)))) */
    /* JADX WARN: Code restructure failed: missing block: B:460:0x12fc, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0533, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0534, code lost:
    
        r15 = r7;
        r1 = r45;
        r17 = r14;
        r14 = r6;
     */
    /* JADX WARN: Path cross not found for [B:483:0x0966, B:240:0x0982], limit reached: 606 */
    /* JADX WARN: Path cross not found for [B:525:0x0d8f, B:350:0x0da2], limit reached: 606 */
    /* JADX WARN: Path cross not found for [B:605:0x08e6, B:220:0x090a], limit reached: 606 */
    /* JADX WARN: Removed duplicated region for block: B:102:0x05a2 A[Catch: Exception -> 0x12fc, TRY_LEAVE, TryCatch #33 {Exception -> 0x12fc, blocks: (B:100:0x059e, B:102:0x05a2), top: B:531:0x059e }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x05cb A[Catch: Exception -> 0x071c, TRY_LEAVE, TryCatch #46 {Exception -> 0x071c, blocks: (B:104:0x05c5, B:106:0x05cb), top: B:555:0x05c5 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x06b6 A[Catch: Exception -> 0x06e1, TRY_LEAVE, TryCatch #24 {Exception -> 0x06e1, blocks: (B:125:0x06ae, B:127:0x06b6), top: B:513:0x06ae }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x06c7  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0734 A[Catch: Exception -> 0x12f1, TRY_LEAVE, TryCatch #13 {Exception -> 0x12f1, blocks: (B:142:0x0730, B:144:0x0734), top: B:491:0x0730 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x080b  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0817 A[Catch: Exception -> 0x12e5, TRY_LEAVE, TryCatch #21 {Exception -> 0x12e5, blocks: (B:172:0x0813, B:174:0x0817), top: B:507:0x0813 }] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0955 A[Catch: Exception -> 0x0adf, TRY_LEAVE, TryCatch #14 {Exception -> 0x0adf, blocks: (B:229:0x094f, B:231:0x0955), top: B:493:0x094f }] */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0abb  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0b62  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0b77 A[Catch: Exception -> 0x0b4a, TRY_LEAVE, TryCatch #55 {Exception -> 0x0b4a, blocks: (B:454:0x12de, B:415:0x10f3, B:417:0x10f7, B:383:0x0f5f, B:385:0x0f63, B:329:0x0cf7, B:331:0x0cfb, B:289:0x0b2c, B:297:0x0b73, B:299:0x0b77), top: B:573:0x0b2c }] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0ba6 A[Catch: Exception -> 0x0cee, TRY_LEAVE, TryCatch #2 {Exception -> 0x0cee, blocks: (B:301:0x0ba0, B:303:0x0ba6), top: B:472:0x0ba0 }] */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0cde  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0cfb A[Catch: Exception -> 0x0b4a, TRY_LEAVE, TryCatch #55 {Exception -> 0x0b4a, blocks: (B:454:0x12de, B:415:0x10f3, B:417:0x10f7, B:383:0x0f5f, B:385:0x0f63, B:329:0x0cf7, B:331:0x0cfb, B:289:0x0b2c, B:297:0x0b73, B:299:0x0b77), top: B:573:0x0b2c }] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0d2f A[Catch: Exception -> 0x0f5a, TRY_LEAVE, TryCatch #17 {Exception -> 0x0f5a, blocks: (B:418:0x1119, B:420:0x111f, B:332:0x0d29, B:334:0x0d2f), top: B:499:0x0d29 }] */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0d71 A[Catch: Exception -> 0x0f3d, TRY_LEAVE, TryCatch #66 {Exception -> 0x0f3d, blocks: (B:338:0x0d6b, B:340:0x0d71), top: B:595:0x0d6b }] */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0eff  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0f57  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0f63 A[Catch: Exception -> 0x0b4a, TRY_LEAVE, TryCatch #55 {Exception -> 0x0b4a, blocks: (B:454:0x12de, B:415:0x10f3, B:417:0x10f7, B:383:0x0f5f, B:385:0x0f63, B:329:0x0cf7, B:331:0x0cfb, B:289:0x0b2c, B:297:0x0b73, B:299:0x0b77), top: B:573:0x0b2c }] */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0f92 A[Catch: Exception -> 0x10e8, TRY_LEAVE, TryCatch #19 {Exception -> 0x10e8, blocks: (B:387:0x0f8c, B:389:0x0f92), top: B:503:0x0f8c }] */
    /* JADX WARN: Removed duplicated region for block: B:412:0x10d4  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x10f7 A[Catch: Exception -> 0x0b4a, TRY_LEAVE, TryCatch #55 {Exception -> 0x0b4a, blocks: (B:454:0x12de, B:415:0x10f3, B:417:0x10f7, B:383:0x0f5f, B:385:0x0f63, B:329:0x0cf7, B:331:0x0cfb, B:289:0x0b2c, B:297:0x0b73, B:299:0x0b77), top: B:573:0x0b2c }] */
    /* JADX WARN: Removed duplicated region for block: B:420:0x111f A[Catch: Exception -> 0x0f5a, TRY_LEAVE, TryCatch #17 {Exception -> 0x0f5a, blocks: (B:418:0x1119, B:420:0x111f, B:332:0x0d29, B:334:0x0d2f), top: B:499:0x0d29 }] */
    /* JADX WARN: Removed duplicated region for block: B:426:0x115d A[Catch: Exception -> 0x12ce, TRY_LEAVE, TryCatch #54 {Exception -> 0x12ce, blocks: (B:424:0x1157, B:426:0x115d), top: B:571:0x1157 }] */
    /* JADX WARN: Removed duplicated region for block: B:444:0x1294  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x12dc  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0937 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0475 A[Catch: Exception -> 0x1305, TRY_LEAVE, TryCatch #37 {Exception -> 0x1305, blocks: (B:68:0x046f, B:70:0x0475), top: B:539:0x046f }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0514 A[Catch: Exception -> 0x052c, TRY_LEAVE, TryCatch #42 {Exception -> 0x052c, blocks: (B:81:0x0508, B:83:0x0514), top: B:549:0x0508 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x051d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:124:0x069c -> B:513:0x06ae). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:237:0x096c -> B:260:0x0a5c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:257:0x0a2f -> B:509:0x0a46). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:313:0x0c73 -> B:565:0x0c8c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:322:0x0cc0 -> B:323:0x0cd0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:337:0x0d56 -> B:595:0x0d6b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:358:0x0e6e -> B:567:0x0e85). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:363:0x0eb1 -> B:364:0x0ece). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:399:0x1064 -> B:517:0x107b). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:408:0x10ad -> B:409:0x10be). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:423:0x1146 -> B:571:0x1157). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:435:0x1233 -> B:587:0x1248). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:440:0x125f -> B:441:0x1273). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x04f6 -> B:549:0x0508). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:89:0x0534 -> B:489:0x054e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:91:0x0540 -> B:489:0x054e). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object tryExtractFromPage(java.lang.String r45, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r46, java.lang.String r47, kotlin.coroutines.Continuation<? super java.lang.Boolean> r48) {
        /*
            Method dump skipped, instruction units count: 4956
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.tryExtractFromPage(java.lang.String, kotlin.jvm.functions.Function1, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object tryExtractFromPage$default(BilibiliProvider bilibiliProvider, String str, Function1 function1, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return bilibiliProvider.tryExtractFromPage(str, function1, str2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String tryExtractFromPage$lambda$4(MatchResult it) {
        String str = (String) CollectionsKt.getOrNull(it.getGroupValues(), 1);
        return str == null ? it.getValue() : str;
    }

    private final boolean isValidVideoUrl(String url) {
        Iterable validDomains = CollectionsKt.listOf(new String[]{"bilibili", "bstar", "bstarstatic", "upos", "akamai", "bilivideo", "hdslb", "acgvideo"});
        Iterable $this$any$iv = validDomains;
        if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
            return false;
        }
        for (Object element$iv : $this$any$iv) {
            String it = (String) element$iv;
            if (StringsKt.contains(url, it, true)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x02fb, code lost:
    
        r14 = r39;
        r13 = r6;
        r16 = r19;
        r15 = r25;
        r19 = r37;
        r6 = r5;
        r5 = r38;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0116 -> B:17:0x0157). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x02cc -> B:45:0x02df). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object extractVideoUrlsFromJson(java.lang.String r37, java.lang.String r38, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r39, kotlin.coroutines.Continuation<? super java.lang.Boolean> r40) {
        /*
            Method dump skipped, instruction units count: 840
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.extractVideoUrlsFromJson(java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String extractVideoUrlsFromJson$lambda$0$0(MatchResult it) {
        return (String) it.getGroupValues().get(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Path cross not found for [B:109:0x0205, B:81:0x0215], limit reached: 131 */
    /* JADX WARN: Path cross not found for [B:133:0x017f, B:58:0x01a0], limit reached: 131 */
    /* JADX WARN: Path cross not found for [B:58:0x01a0, B:133:0x017f], limit reached: 131 */
    /* JADX WARN: Removed duplicated region for block: B:115:0x014b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x017f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01bd A[Catch: Exception -> 0x02b5, TryCatch #7 {Exception -> 0x02b5, blocks: (B:29:0x0114, B:35:0x0143, B:43:0x0171, B:47:0x0179, B:59:0x01b5, B:61:0x01bd, B:63:0x01c3, B:58:0x01a0, B:42:0x0166, B:34:0x0139, B:39:0x014b, B:31:0x0120), top: B:121:0x0114, inners: #3, #10 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01e0 A[Catch: Exception -> 0x02a7, TryCatch #11 {Exception -> 0x02a7, blocks: (B:65:0x01da, B:67:0x01e0, B:69:0x01f3, B:72:0x01ff, B:81:0x0215), top: B:127:0x01da }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:86:0x0268 -> B:111:0x027f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadSubtitles(java.lang.String r23, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            Method dump skipped, instruction units count: 772
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.BilibiliProvider.loadSubtitles(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String ensureHttps(String $this$ensureHttps) {
        return StringsKt.startsWith$default($this$ensureHttps, "//", false, 2, (Object) null) ? "https:" + $this$ensureHttps : StringsKt.startsWith$default($this$ensureHttps, "http://", false, 2, (Object) null) ? StringsKt.replace$default($this$ensureHttps, "http://", "https://", false, 4, (Object) null) : $this$ensureHttps;
    }

    private final String generateDashManifest(String videoUrl, String audioUrl, int quality) {
        int width = 1920;
        switch (quality) {
            case 16:
                width = 640;
                break;
            case 32:
                width = 854;
                break;
            case 64:
                width = 1280;
                break;
            case 80:
            case 112:
                break;
            default:
                width = 1280;
                break;
        }
        int height = 1080;
        switch (quality) {
            case 16:
                height = 360;
                break;
            case 32:
                height = 480;
                break;
            case 64:
                height = 720;
                break;
            case 80:
            case 112:
                break;
            default:
                height = 720;
                break;
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<MPD xmlns=\"urn:mpeg:dash:schema:mpd:2011\" profiles=\"urn:mpeg:dash:profile:isoff-on-demand:2011\" type=\"static\" minBufferTime=\"PT1.5S\" mediaPresentationDuration=\"PT1H\">\n  <Period>\n    <AdaptationSet mimeType=\"video/mp4\" contentType=\"video\">\n      <Representation id=\"video\" bandwidth=\"2000000\" width=\"" + width + "\" height=\"" + height + "\">\n        <BaseURL>" + videoUrl + "</BaseURL>\n      </Representation>\n    </AdaptationSet>\n    <AdaptationSet mimeType=\"audio/mp4\" contentType=\"audio\" lang=\"und\">\n      <Representation id=\"audio\" bandwidth=\"128000\">\n        <BaseURL>" + audioUrl + "</BaseURL>\n      </Representation>\n    </AdaptationSet>\n  </Period>\n</MPD>";
    }

    private final Qualities getQualityFromId(int qn) {
        switch (qn) {
            case 16:
                return Qualities.P360;
            case 32:
                return Qualities.P480;
            case 64:
                return Qualities.P720;
            case 74:
                return Qualities.P720;
            case 80:
                return Qualities.P1080;
            case 112:
                return Qualities.P1080;
            case 116:
                return Qualities.P1080;
            case 120:
                return Qualities.P2160;
            case 125:
                return Qualities.P2160;
            case 126:
                return Qualities.P2160;
            case 127:
                return Qualities.P2160;
            default:
                return Qualities.Unknown;
        }
    }

    private final String generateDashMpdUrl(String videoUrl, String audioUrl, int quality) throws UnsupportedEncodingException {
        String encodedVideoUrl = URLEncoder.encode(videoUrl, "UTF-8");
        String encodedAudioUrl = URLEncoder.encode(audioUrl, "UTF-8");
        return "https://www.bilibili.tv/mpd?video=" + encodedVideoUrl + "&audio=" + encodedAudioUrl + "&quality=" + quality;
    }

    private final Qualities getQualityFromResolution(int height) {
        return height >= 2160 ? Qualities.P2160 : height >= 1440 ? Qualities.P1440 : height >= 1080 ? Qualities.P1080 : height >= 720 ? Qualities.P720 : height >= 480 ? Qualities.P480 : height >= 360 ? Qualities.P360 : Qualities.Unknown;
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$EpisodeData;", "", "epId", "", "seasonId", "aid", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEpId", "()Ljava/lang/String;", "getSeasonId", "getAid", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class EpisodeData {

        @JsonProperty("aid")
        @Nullable
        private final String aid;

        @JsonProperty("epId")
        @Nullable
        private final String epId;

        @JsonProperty("seasonId")
        @Nullable
        private final String seasonId;

        public EpisodeData() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ EpisodeData copy$default(EpisodeData episodeData, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = episodeData.epId;
            }
            if ((i & 2) != 0) {
                str2 = episodeData.seasonId;
            }
            if ((i & 4) != 0) {
                str3 = episodeData.aid;
            }
            return episodeData.copy(str, str2, str3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEpId() {
            return this.epId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAid() {
            return this.aid;
        }

        @NotNull
        public final EpisodeData copy(@JsonProperty("epId") @Nullable String epId, @JsonProperty("seasonId") @Nullable String seasonId, @JsonProperty("aid") @Nullable String aid) {
            return new EpisodeData(epId, seasonId, aid);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EpisodeData)) {
                return false;
            }
            EpisodeData episodeData = (EpisodeData) other;
            return Intrinsics.areEqual(this.epId, episodeData.epId) && Intrinsics.areEqual(this.seasonId, episodeData.seasonId) && Intrinsics.areEqual(this.aid, episodeData.aid);
        }

        public int hashCode() {
            return ((((this.epId == null ? 0 : this.epId.hashCode()) * 31) + (this.seasonId == null ? 0 : this.seasonId.hashCode())) * 31) + (this.aid != null ? this.aid.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "EpisodeData(epId=" + this.epId + ", seasonId=" + this.seasonId + ", aid=" + this.aid + ')';
        }

        public EpisodeData(@JsonProperty("epId") @Nullable String epId, @JsonProperty("seasonId") @Nullable String seasonId, @JsonProperty("aid") @Nullable String aid) {
            this.epId = epId;
            this.seasonId = seasonId;
            this.aid = aid;
        }

        public /* synthetic */ EpisodeData(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        @Nullable
        public final String getEpId() {
            return this.epId;
        }

        @Nullable
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        public final String getAid() {
            return this.aid;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSearchResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/BilibiliProvider$BiliSearchData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliSearchData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliSearchData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliSearchData;)Lcom/cncverse/BilibiliProvider$BiliSearchResponse;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSearchResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliSearchData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public BiliSearchResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliSearchResponse copy$default(BiliSearchResponse biliSearchResponse, Integer num, String str, BiliSearchData biliSearchData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliSearchResponse.code;
            }
            if ((i & 2) != 0) {
                str = biliSearchResponse.message;
            }
            if ((i & 4) != 0) {
                biliSearchData = biliSearchResponse.data;
            }
            return biliSearchResponse.copy(num, str, biliSearchData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BiliSearchData getData() {
            return this.data;
        }

        @NotNull
        public final BiliSearchResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliSearchData data) {
            return new BiliSearchResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSearchResponse)) {
                return false;
            }
            BiliSearchResponse biliSearchResponse = (BiliSearchResponse) other;
            return Intrinsics.areEqual(this.code, biliSearchResponse.code) && Intrinsics.areEqual(this.message, biliSearchResponse.message) && Intrinsics.areEqual(this.data, biliSearchResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSearchResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public BiliSearchResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliSearchData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ BiliSearchResponse(Integer num, String str, BiliSearchData biliSearchData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : biliSearchData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final BiliSearchData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ,\u0010\u0010\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSearchData;", "", "modules", "", "Lcom/cncverse/BilibiliProvider$BiliSearchModule;", "hasNext", "", "<init>", "(Ljava/util/List;Ljava/lang/Boolean;)V", "getModules", "()Ljava/util/List;", "getHasNext", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "copy", "(Ljava/util/List;Ljava/lang/Boolean;)Lcom/cncverse/BilibiliProvider$BiliSearchData;", "equals", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSearchData {

        @JsonProperty("has_next")
        @Nullable
        private final Boolean hasNext;

        @JsonProperty("modules")
        @Nullable
        private final List<BiliSearchModule> modules;

        /* JADX WARN: Multi-variable type inference failed */
        public BiliSearchData() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliSearchData copy$default(BiliSearchData biliSearchData, List list, Boolean bool, int i, Object obj) {
            if ((i & 1) != 0) {
                list = biliSearchData.modules;
            }
            if ((i & 2) != 0) {
                bool = biliSearchData.hasNext;
            }
            return biliSearchData.copy(list, bool);
        }

        @Nullable
        public final List<BiliSearchModule> component1() {
            return this.modules;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Boolean getHasNext() {
            return this.hasNext;
        }

        @NotNull
        public final BiliSearchData copy(@JsonProperty("modules") @Nullable List<BiliSearchModule> modules, @JsonProperty("has_next") @Nullable Boolean hasNext) {
            return new BiliSearchData(modules, hasNext);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSearchData)) {
                return false;
            }
            BiliSearchData biliSearchData = (BiliSearchData) other;
            return Intrinsics.areEqual(this.modules, biliSearchData.modules) && Intrinsics.areEqual(this.hasNext, biliSearchData.hasNext);
        }

        public int hashCode() {
            return ((this.modules == null ? 0 : this.modules.hashCode()) * 31) + (this.hasNext != null ? this.hasNext.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSearchData(modules=" + this.modules + ", hasNext=" + this.hasNext + ')';
        }

        public BiliSearchData(@JsonProperty("modules") @Nullable List<BiliSearchModule> list, @JsonProperty("has_next") @Nullable Boolean hasNext) {
            this.modules = list;
            this.hasNext = hasNext;
        }

        public /* synthetic */ BiliSearchData(List list, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : bool);
        }

        @Nullable
        public final List<BiliSearchModule> getModules() {
            return this.modules;
        }

        @Nullable
        public final Boolean getHasNext() {
            return this.hasNext;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSearchModule;", "", "type", "", "title", "items", "", "Lcom/cncverse/BilibiliProvider$BiliSearchItem;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getType", "()Ljava/lang/String;", "getTitle", "getItems", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSearchModule {

        @JsonProperty("items")
        @Nullable
        private final List<BiliSearchItem> items;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("type")
        @Nullable
        private final String type;

        public BiliSearchModule() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliSearchModule copy$default(BiliSearchModule biliSearchModule, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliSearchModule.type;
            }
            if ((i & 2) != 0) {
                str2 = biliSearchModule.title;
            }
            if ((i & 4) != 0) {
                list = biliSearchModule.items;
            }
            return biliSearchModule.copy(str, str2, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final List<BiliSearchItem> component3() {
            return this.items;
        }

        @NotNull
        public final BiliSearchModule copy(@JsonProperty("type") @Nullable String type, @JsonProperty("title") @Nullable String title, @JsonProperty("items") @Nullable List<BiliSearchItem> items) {
            return new BiliSearchModule(type, title, items);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSearchModule)) {
                return false;
            }
            BiliSearchModule biliSearchModule = (BiliSearchModule) other;
            return Intrinsics.areEqual(this.type, biliSearchModule.type) && Intrinsics.areEqual(this.title, biliSearchModule.title) && Intrinsics.areEqual(this.items, biliSearchModule.items);
        }

        public int hashCode() {
            return ((((this.type == null ? 0 : this.type.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.items != null ? this.items.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSearchModule(type=" + this.type + ", title=" + this.title + ", items=" + this.items + ')';
        }

        public BiliSearchModule(@JsonProperty("type") @Nullable String type, @JsonProperty("title") @Nullable String title, @JsonProperty("items") @Nullable List<BiliSearchItem> list) {
            this.type = type;
            this.title = title;
            this.items = list;
        }

        public /* synthetic */ BiliSearchModule(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list);
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final List<BiliSearchItem> getItems() {
            return this.items;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\u001b\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSearchItem;", "", "title", "", "seasonId", "cover", "aid", "view", "seasonType", "description", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getSeasonId", "getCover", "getAid", "getView", "getSeasonType", "getDescription", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSearchItem {

        @JsonProperty("aid")
        @Nullable
        private final String aid;

        @JsonProperty("cover")
        @Nullable
        private final String cover;

        @JsonProperty("description")
        @Nullable
        private final String description;

        @JsonProperty("season_id")
        @Nullable
        private final String seasonId;

        @JsonProperty("season_type")
        @Nullable
        private final String seasonType;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("view")
        @Nullable
        private final String view;

        public BiliSearchItem() {
            this(null, null, null, null, null, null, null, 127, null);
        }

        public static /* synthetic */ BiliSearchItem copy$default(BiliSearchItem biliSearchItem, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliSearchItem.title;
            }
            if ((i & 2) != 0) {
                str2 = biliSearchItem.seasonId;
            }
            if ((i & 4) != 0) {
                str3 = biliSearchItem.cover;
            }
            if ((i & 8) != 0) {
                str4 = biliSearchItem.aid;
            }
            if ((i & 16) != 0) {
                str5 = biliSearchItem.view;
            }
            if ((i & 32) != 0) {
                str6 = biliSearchItem.seasonType;
            }
            if ((i & 64) != 0) {
                str7 = biliSearchItem.description;
            }
            String str8 = str6;
            String str9 = str7;
            String str10 = str5;
            String str11 = str3;
            return biliSearchItem.copy(str, str2, str11, str4, str10, str8, str9);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getAid() {
            return this.aid;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getView() {
            return this.view;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSeasonType() {
            return this.seasonType;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        @NotNull
        public final BiliSearchItem copy(@JsonProperty("title") @Nullable String title, @JsonProperty("season_id") @Nullable String seasonId, @JsonProperty("cover") @Nullable String cover, @JsonProperty("aid") @Nullable String aid, @JsonProperty("view") @Nullable String view, @JsonProperty("season_type") @Nullable String seasonType, @JsonProperty("description") @Nullable String description) {
            return new BiliSearchItem(title, seasonId, cover, aid, view, seasonType, description);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSearchItem)) {
                return false;
            }
            BiliSearchItem biliSearchItem = (BiliSearchItem) other;
            return Intrinsics.areEqual(this.title, biliSearchItem.title) && Intrinsics.areEqual(this.seasonId, biliSearchItem.seasonId) && Intrinsics.areEqual(this.cover, biliSearchItem.cover) && Intrinsics.areEqual(this.aid, biliSearchItem.aid) && Intrinsics.areEqual(this.view, biliSearchItem.view) && Intrinsics.areEqual(this.seasonType, biliSearchItem.seasonType) && Intrinsics.areEqual(this.description, biliSearchItem.description);
        }

        public int hashCode() {
            return ((((((((((((this.title == null ? 0 : this.title.hashCode()) * 31) + (this.seasonId == null ? 0 : this.seasonId.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.aid == null ? 0 : this.aid.hashCode())) * 31) + (this.view == null ? 0 : this.view.hashCode())) * 31) + (this.seasonType == null ? 0 : this.seasonType.hashCode())) * 31) + (this.description != null ? this.description.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSearchItem(title=" + this.title + ", seasonId=" + this.seasonId + ", cover=" + this.cover + ", aid=" + this.aid + ", view=" + this.view + ", seasonType=" + this.seasonType + ", description=" + this.description + ')';
        }

        public BiliSearchItem(@JsonProperty("title") @Nullable String title, @JsonProperty("season_id") @Nullable String seasonId, @JsonProperty("cover") @Nullable String cover, @JsonProperty("aid") @Nullable String aid, @JsonProperty("view") @Nullable String view, @JsonProperty("season_type") @Nullable String seasonType, @JsonProperty("description") @Nullable String description) {
            this.title = title;
            this.seasonId = seasonId;
            this.cover = cover;
            this.aid = aid;
            this.view = view;
            this.seasonType = seasonType;
            this.description = description;
        }

        public /* synthetic */ BiliSearchItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7);
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getAid() {
            return this.aid;
        }

        @Nullable
        public final String getView() {
            return this.view;
        }

        @Nullable
        public final String getSeasonType() {
            return this.seasonType;
        }

        @Nullable
        public final String getDescription() {
            return this.description;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSeasonInfoResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/BilibiliProvider$BiliSeasonInfoData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliSeasonInfoData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliSeasonInfoData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliSeasonInfoData;)Lcom/cncverse/BilibiliProvider$BiliSeasonInfoResponse;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSeasonInfoResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliSeasonInfoData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public BiliSeasonInfoResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliSeasonInfoResponse copy$default(BiliSeasonInfoResponse biliSeasonInfoResponse, Integer num, String str, BiliSeasonInfoData biliSeasonInfoData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliSeasonInfoResponse.code;
            }
            if ((i & 2) != 0) {
                str = biliSeasonInfoResponse.message;
            }
            if ((i & 4) != 0) {
                biliSeasonInfoData = biliSeasonInfoResponse.data;
            }
            return biliSeasonInfoResponse.copy(num, str, biliSeasonInfoData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BiliSeasonInfoData getData() {
            return this.data;
        }

        @NotNull
        public final BiliSeasonInfoResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliSeasonInfoData data) {
            return new BiliSeasonInfoResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSeasonInfoResponse)) {
                return false;
            }
            BiliSeasonInfoResponse biliSeasonInfoResponse = (BiliSeasonInfoResponse) other;
            return Intrinsics.areEqual(this.code, biliSeasonInfoResponse.code) && Intrinsics.areEqual(this.message, biliSeasonInfoResponse.message) && Intrinsics.areEqual(this.data, biliSeasonInfoResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSeasonInfoResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public BiliSeasonInfoResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliSeasonInfoData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ BiliSeasonInfoResponse(Integer num, String str, BiliSeasonInfoData biliSeasonInfoData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : biliSeasonInfoData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final BiliSeasonInfoData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSeasonInfoData;", "", "season", "Lcom/cncverse/BilibiliProvider$BiliSeason;", "<init>", "(Lcom/cncverse/BilibiliProvider$BiliSeason;)V", "getSeason", "()Lcom/cncverse/BilibiliProvider$BiliSeason;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSeasonInfoData {

        @JsonProperty("season")
        @Nullable
        private final BiliSeason season;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliSeasonInfoData() {
            BiliSeason biliSeason = null;
            this(biliSeason, 1, biliSeason);
        }

        public static /* synthetic */ BiliSeasonInfoData copy$default(BiliSeasonInfoData biliSeasonInfoData, BiliSeason biliSeason, int i, Object obj) {
            if ((i & 1) != 0) {
                biliSeason = biliSeasonInfoData.season;
            }
            return biliSeasonInfoData.copy(biliSeason);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final BiliSeason getSeason() {
            return this.season;
        }

        @NotNull
        public final BiliSeasonInfoData copy(@JsonProperty("season") @Nullable BiliSeason season) {
            return new BiliSeasonInfoData(season);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiliSeasonInfoData) && Intrinsics.areEqual(this.season, ((BiliSeasonInfoData) other).season);
        }

        public int hashCode() {
            if (this.season == null) {
                return 0;
            }
            return this.season.hashCode();
        }

        @NotNull
        public String toString() {
            return "BiliSeasonInfoData(season=" + this.season + ')';
        }

        public BiliSeasonInfoData(@JsonProperty("season") @Nullable BiliSeason season) {
            this.season = season;
        }

        public /* synthetic */ BiliSeasonInfoData(BiliSeason biliSeason, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : biliSeason);
        }

        @Nullable
        public final BiliSeason getSeason() {
            return this.season;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001By\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010!\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J{\u0010$\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010(\u001a\u00020)HÖ\u0081\u0004J\n\u0010*\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011¨\u0006+"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSeason;", "", "seasonId", "", "title", "description", "verticalCover", "horizontalCover", "playerDate", "styles", "", "Lcom/cncverse/BilibiliProvider$BiliStyle;", "view", "seasonType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getSeasonId", "()Ljava/lang/String;", "getTitle", "getDescription", "getVerticalCover", "getHorizontalCover", "getPlayerDate", "getStyles", "()Ljava/util/List;", "getView", "getSeasonType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSeason {

        @JsonProperty("description")
        @Nullable
        private final String description;

        @JsonProperty("horizontal_cover")
        @Nullable
        private final String horizontalCover;

        @JsonProperty("player_date")
        @Nullable
        private final String playerDate;

        @JsonProperty("season_id")
        @Nullable
        private final String seasonId;

        @JsonProperty("season_type")
        @Nullable
        private final String seasonType;

        @JsonProperty("styles")
        @Nullable
        private final List<BiliStyle> styles;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("vertical_cover")
        @Nullable
        private final String verticalCover;

        @JsonProperty("view")
        @Nullable
        private final String view;

        public BiliSeason() {
            this(null, null, null, null, null, null, null, null, null, 511, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliSeason copy$default(BiliSeason biliSeason, String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, String str8, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliSeason.seasonId;
            }
            if ((i & 2) != 0) {
                str2 = biliSeason.title;
            }
            if ((i & 4) != 0) {
                str3 = biliSeason.description;
            }
            if ((i & 8) != 0) {
                str4 = biliSeason.verticalCover;
            }
            if ((i & 16) != 0) {
                str5 = biliSeason.horizontalCover;
            }
            if ((i & 32) != 0) {
                str6 = biliSeason.playerDate;
            }
            if ((i & 64) != 0) {
                list = biliSeason.styles;
            }
            if ((i & 128) != 0) {
                str7 = biliSeason.view;
            }
            if ((i & 256) != 0) {
                str8 = biliSeason.seasonType;
            }
            String str9 = str7;
            String str10 = str8;
            String str11 = str6;
            List list2 = list;
            String str12 = str5;
            String str13 = str3;
            return biliSeason.copy(str, str2, str13, str4, str12, str11, list2, str9, str10);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDescription() {
            return this.description;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getVerticalCover() {
            return this.verticalCover;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getHorizontalCover() {
            return this.horizontalCover;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPlayerDate() {
            return this.playerDate;
        }

        @Nullable
        public final List<BiliStyle> component7() {
            return this.styles;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getView() {
            return this.view;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getSeasonType() {
            return this.seasonType;
        }

        @NotNull
        public final BiliSeason copy(@JsonProperty("season_id") @Nullable String seasonId, @JsonProperty("title") @Nullable String title, @JsonProperty("description") @Nullable String description, @JsonProperty("vertical_cover") @Nullable String verticalCover, @JsonProperty("horizontal_cover") @Nullable String horizontalCover, @JsonProperty("player_date") @Nullable String playerDate, @JsonProperty("styles") @Nullable List<BiliStyle> styles, @JsonProperty("view") @Nullable String view, @JsonProperty("season_type") @Nullable String seasonType) {
            return new BiliSeason(seasonId, title, description, verticalCover, horizontalCover, playerDate, styles, view, seasonType);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSeason)) {
                return false;
            }
            BiliSeason biliSeason = (BiliSeason) other;
            return Intrinsics.areEqual(this.seasonId, biliSeason.seasonId) && Intrinsics.areEqual(this.title, biliSeason.title) && Intrinsics.areEqual(this.description, biliSeason.description) && Intrinsics.areEqual(this.verticalCover, biliSeason.verticalCover) && Intrinsics.areEqual(this.horizontalCover, biliSeason.horizontalCover) && Intrinsics.areEqual(this.playerDate, biliSeason.playerDate) && Intrinsics.areEqual(this.styles, biliSeason.styles) && Intrinsics.areEqual(this.view, biliSeason.view) && Intrinsics.areEqual(this.seasonType, biliSeason.seasonType);
        }

        public int hashCode() {
            return ((((((((((((((((this.seasonId == null ? 0 : this.seasonId.hashCode()) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.verticalCover == null ? 0 : this.verticalCover.hashCode())) * 31) + (this.horizontalCover == null ? 0 : this.horizontalCover.hashCode())) * 31) + (this.playerDate == null ? 0 : this.playerDate.hashCode())) * 31) + (this.styles == null ? 0 : this.styles.hashCode())) * 31) + (this.view == null ? 0 : this.view.hashCode())) * 31) + (this.seasonType != null ? this.seasonType.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSeason(seasonId=" + this.seasonId + ", title=" + this.title + ", description=" + this.description + ", verticalCover=" + this.verticalCover + ", horizontalCover=" + this.horizontalCover + ", playerDate=" + this.playerDate + ", styles=" + this.styles + ", view=" + this.view + ", seasonType=" + this.seasonType + ')';
        }

        public BiliSeason(@JsonProperty("season_id") @Nullable String seasonId, @JsonProperty("title") @Nullable String title, @JsonProperty("description") @Nullable String description, @JsonProperty("vertical_cover") @Nullable String verticalCover, @JsonProperty("horizontal_cover") @Nullable String horizontalCover, @JsonProperty("player_date") @Nullable String playerDate, @JsonProperty("styles") @Nullable List<BiliStyle> list, @JsonProperty("view") @Nullable String view, @JsonProperty("season_type") @Nullable String seasonType) {
            this.seasonId = seasonId;
            this.title = title;
            this.description = description;
            this.verticalCover = verticalCover;
            this.horizontalCover = horizontalCover;
            this.playerDate = playerDate;
            this.styles = list;
            this.view = view;
            this.seasonType = seasonType;
        }

        public /* synthetic */ BiliSeason(String str, String str2, String str3, String str4, String str5, String str6, List list, String str7, String str8, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : list, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : str8);
        }

        @Nullable
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getDescription() {
            return this.description;
        }

        @Nullable
        public final String getVerticalCover() {
            return this.verticalCover;
        }

        @Nullable
        public final String getHorizontalCover() {
            return this.horizontalCover;
        }

        @Nullable
        public final String getPlayerDate() {
            return this.playerDate;
        }

        @Nullable
        public final List<BiliStyle> getStyles() {
            return this.styles;
        }

        @Nullable
        public final String getView() {
            return this.view;
        }

        @Nullable
        public final String getSeasonType() {
            return this.seasonType;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliStyle;", "", "id", "", "title", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTitle", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/cncverse/BilibiliProvider$BiliStyle;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliStyle {

        @JsonProperty("id")
        @Nullable
        private final Integer id;

        @JsonProperty("title")
        @Nullable
        private final String title;

        /* JADX WARN: Multi-variable type inference failed */
        public BiliStyle() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ BiliStyle copy$default(BiliStyle biliStyle, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliStyle.id;
            }
            if ((i & 2) != 0) {
                str = biliStyle.title;
            }
            return biliStyle.copy(num, str);
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

        @NotNull
        public final BiliStyle copy(@JsonProperty("id") @Nullable Integer id, @JsonProperty("title") @Nullable String title) {
            return new BiliStyle(id, title);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliStyle)) {
                return false;
            }
            BiliStyle biliStyle = (BiliStyle) other;
            return Intrinsics.areEqual(this.id, biliStyle.id) && Intrinsics.areEqual(this.title, biliStyle.title);
        }

        public int hashCode() {
            return ((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.title != null ? this.title.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliStyle(id=" + this.id + ", title=" + this.title + ')';
        }

        public BiliStyle(@JsonProperty("id") @Nullable Integer id, @JsonProperty("title") @Nullable String title) {
            this.id = id;
            this.title = title;
        }

        public /* synthetic */ BiliStyle(Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str);
        }

        @Nullable
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliEpisodesResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/BilibiliProvider$BiliEpisodesData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliEpisodesData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliEpisodesData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliEpisodesData;)Lcom/cncverse/BilibiliProvider$BiliEpisodesResponse;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliEpisodesResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliEpisodesData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public BiliEpisodesResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliEpisodesResponse copy$default(BiliEpisodesResponse biliEpisodesResponse, Integer num, String str, BiliEpisodesData biliEpisodesData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliEpisodesResponse.code;
            }
            if ((i & 2) != 0) {
                str = biliEpisodesResponse.message;
            }
            if ((i & 4) != 0) {
                biliEpisodesData = biliEpisodesResponse.data;
            }
            return biliEpisodesResponse.copy(num, str, biliEpisodesData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BiliEpisodesData getData() {
            return this.data;
        }

        @NotNull
        public final BiliEpisodesResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliEpisodesData data) {
            return new BiliEpisodesResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliEpisodesResponse)) {
                return false;
            }
            BiliEpisodesResponse biliEpisodesResponse = (BiliEpisodesResponse) other;
            return Intrinsics.areEqual(this.code, biliEpisodesResponse.code) && Intrinsics.areEqual(this.message, biliEpisodesResponse.message) && Intrinsics.areEqual(this.data, biliEpisodesResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliEpisodesResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public BiliEpisodesResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliEpisodesData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ BiliEpisodesResponse(Integer num, String str, BiliEpisodesData biliEpisodesData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : biliEpisodesData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final BiliEpisodesData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliEpisodesData;", "", "sections", "", "Lcom/cncverse/BilibiliProvider$BiliSection;", "<init>", "(Ljava/util/List;)V", "getSections", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliEpisodesData {

        @JsonProperty("sections")
        @Nullable
        private final List<BiliSection> sections;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliEpisodesData() {
            List list = null;
            this(list, 1, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliEpisodesData copy$default(BiliEpisodesData biliEpisodesData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = biliEpisodesData.sections;
            }
            return biliEpisodesData.copy(list);
        }

        @Nullable
        public final List<BiliSection> component1() {
            return this.sections;
        }

        @NotNull
        public final BiliEpisodesData copy(@JsonProperty("sections") @Nullable List<BiliSection> sections) {
            return new BiliEpisodesData(sections);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiliEpisodesData) && Intrinsics.areEqual(this.sections, ((BiliEpisodesData) other).sections);
        }

        public int hashCode() {
            if (this.sections == null) {
                return 0;
            }
            return this.sections.hashCode();
        }

        @NotNull
        public String toString() {
            return "BiliEpisodesData(sections=" + this.sections + ')';
        }

        public BiliEpisodesData(@JsonProperty("sections") @Nullable List<BiliSection> list) {
            this.sections = list;
        }

        public /* synthetic */ BiliEpisodesData(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list);
        }

        @Nullable
        public final List<BiliSection> getSections() {
            return this.sections;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSection;", "", "title", "", "epListTitle", "episodes", "", "Lcom/cncverse/BilibiliProvider$BiliEpisode;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getTitle", "()Ljava/lang/String;", "getEpListTitle", "getEpisodes", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSection {

        @JsonProperty("ep_list_title")
        @Nullable
        private final String epListTitle;

        @JsonProperty("episodes")
        @Nullable
        private final List<BiliEpisode> episodes;

        @JsonProperty("title")
        @Nullable
        private final String title;

        public BiliSection() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliSection copy$default(BiliSection biliSection, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliSection.title;
            }
            if ((i & 2) != 0) {
                str2 = biliSection.epListTitle;
            }
            if ((i & 4) != 0) {
                list = biliSection.episodes;
            }
            return biliSection.copy(str, str2, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEpListTitle() {
            return this.epListTitle;
        }

        @Nullable
        public final List<BiliEpisode> component3() {
            return this.episodes;
        }

        @NotNull
        public final BiliSection copy(@JsonProperty("title") @Nullable String title, @JsonProperty("ep_list_title") @Nullable String epListTitle, @JsonProperty("episodes") @Nullable List<BiliEpisode> episodes) {
            return new BiliSection(title, epListTitle, episodes);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSection)) {
                return false;
            }
            BiliSection biliSection = (BiliSection) other;
            return Intrinsics.areEqual(this.title, biliSection.title) && Intrinsics.areEqual(this.epListTitle, biliSection.epListTitle) && Intrinsics.areEqual(this.episodes, biliSection.episodes);
        }

        public int hashCode() {
            return ((((this.title == null ? 0 : this.title.hashCode()) * 31) + (this.epListTitle == null ? 0 : this.epListTitle.hashCode())) * 31) + (this.episodes != null ? this.episodes.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSection(title=" + this.title + ", epListTitle=" + this.epListTitle + ", episodes=" + this.episodes + ')';
        }

        public BiliSection(@JsonProperty("title") @Nullable String title, @JsonProperty("ep_list_title") @Nullable String epListTitle, @JsonProperty("episodes") @Nullable List<BiliEpisode> list) {
            this.title = title;
            this.epListTitle = epListTitle;
            this.episodes = list;
        }

        public /* synthetic */ BiliSection(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list);
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getEpListTitle() {
            return this.epListTitle;
        }

        @Nullable
        public final List<BiliEpisode> getEpisodes() {
            return this.episodes;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003JQ\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliEpisode;", "", "episodeId", "", "cover", "titleDisplay", "shortTitleDisplay", "longTitleDisplay", "publishTime", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEpisodeId", "()Ljava/lang/String;", "getCover", "getTitleDisplay", "getShortTitleDisplay", "getLongTitleDisplay", "getPublishTime", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliEpisode {

        @JsonProperty("cover")
        @Nullable
        private final String cover;

        @JsonProperty("episode_id")
        @Nullable
        private final String episodeId;

        @JsonProperty("long_title_display")
        @Nullable
        private final String longTitleDisplay;

        @JsonProperty("publish_time")
        @Nullable
        private final String publishTime;

        @JsonProperty("short_title_display")
        @Nullable
        private final String shortTitleDisplay;

        @JsonProperty("title_display")
        @Nullable
        private final String titleDisplay;

        public BiliEpisode() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ BiliEpisode copy$default(BiliEpisode biliEpisode, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliEpisode.episodeId;
            }
            if ((i & 2) != 0) {
                str2 = biliEpisode.cover;
            }
            if ((i & 4) != 0) {
                str3 = biliEpisode.titleDisplay;
            }
            if ((i & 8) != 0) {
                str4 = biliEpisode.shortTitleDisplay;
            }
            if ((i & 16) != 0) {
                str5 = biliEpisode.longTitleDisplay;
            }
            if ((i & 32) != 0) {
                str6 = biliEpisode.publishTime;
            }
            String str7 = str5;
            String str8 = str6;
            return biliEpisode.copy(str, str2, str3, str4, str7, str8);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEpisodeId() {
            return this.episodeId;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitleDisplay() {
            return this.titleDisplay;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getShortTitleDisplay() {
            return this.shortTitleDisplay;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getLongTitleDisplay() {
            return this.longTitleDisplay;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getPublishTime() {
            return this.publishTime;
        }

        @NotNull
        public final BiliEpisode copy(@JsonProperty("episode_id") @Nullable String episodeId, @JsonProperty("cover") @Nullable String cover, @JsonProperty("title_display") @Nullable String titleDisplay, @JsonProperty("short_title_display") @Nullable String shortTitleDisplay, @JsonProperty("long_title_display") @Nullable String longTitleDisplay, @JsonProperty("publish_time") @Nullable String publishTime) {
            return new BiliEpisode(episodeId, cover, titleDisplay, shortTitleDisplay, longTitleDisplay, publishTime);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliEpisode)) {
                return false;
            }
            BiliEpisode biliEpisode = (BiliEpisode) other;
            return Intrinsics.areEqual(this.episodeId, biliEpisode.episodeId) && Intrinsics.areEqual(this.cover, biliEpisode.cover) && Intrinsics.areEqual(this.titleDisplay, biliEpisode.titleDisplay) && Intrinsics.areEqual(this.shortTitleDisplay, biliEpisode.shortTitleDisplay) && Intrinsics.areEqual(this.longTitleDisplay, biliEpisode.longTitleDisplay) && Intrinsics.areEqual(this.publishTime, biliEpisode.publishTime);
        }

        public int hashCode() {
            return ((((((((((this.episodeId == null ? 0 : this.episodeId.hashCode()) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.titleDisplay == null ? 0 : this.titleDisplay.hashCode())) * 31) + (this.shortTitleDisplay == null ? 0 : this.shortTitleDisplay.hashCode())) * 31) + (this.longTitleDisplay == null ? 0 : this.longTitleDisplay.hashCode())) * 31) + (this.publishTime != null ? this.publishTime.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliEpisode(episodeId=" + this.episodeId + ", cover=" + this.cover + ", titleDisplay=" + this.titleDisplay + ", shortTitleDisplay=" + this.shortTitleDisplay + ", longTitleDisplay=" + this.longTitleDisplay + ", publishTime=" + this.publishTime + ')';
        }

        public BiliEpisode(@JsonProperty("episode_id") @Nullable String episodeId, @JsonProperty("cover") @Nullable String cover, @JsonProperty("title_display") @Nullable String titleDisplay, @JsonProperty("short_title_display") @Nullable String shortTitleDisplay, @JsonProperty("long_title_display") @Nullable String longTitleDisplay, @JsonProperty("publish_time") @Nullable String publishTime) {
            this.episodeId = episodeId;
            this.cover = cover;
            this.titleDisplay = titleDisplay;
            this.shortTitleDisplay = shortTitleDisplay;
            this.longTitleDisplay = longTitleDisplay;
            this.publishTime = publishTime;
        }

        public /* synthetic */ BiliEpisode(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6);
        }

        @Nullable
        public final String getEpisodeId() {
            return this.episodeId;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getTitleDisplay() {
            return this.titleDisplay;
        }

        @Nullable
        public final String getShortTitleDisplay() {
            return this.shortTitleDisplay;
        }

        @Nullable
        public final String getLongTitleDisplay() {
            return this.longTitleDisplay;
        }

        @Nullable
        public final String getPublishTime() {
            return this.publishTime;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSubtitleResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/BilibiliProvider$BiliSubtitleData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliSubtitleData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliSubtitleData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliSubtitleData;)Lcom/cncverse/BilibiliProvider$BiliSubtitleResponse;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSubtitleResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliSubtitleData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public BiliSubtitleResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliSubtitleResponse copy$default(BiliSubtitleResponse biliSubtitleResponse, Integer num, String str, BiliSubtitleData biliSubtitleData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliSubtitleResponse.code;
            }
            if ((i & 2) != 0) {
                str = biliSubtitleResponse.message;
            }
            if ((i & 4) != 0) {
                biliSubtitleData = biliSubtitleResponse.data;
            }
            return biliSubtitleResponse.copy(num, str, biliSubtitleData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BiliSubtitleData getData() {
            return this.data;
        }

        @NotNull
        public final BiliSubtitleResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliSubtitleData data) {
            return new BiliSubtitleResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSubtitleResponse)) {
                return false;
            }
            BiliSubtitleResponse biliSubtitleResponse = (BiliSubtitleResponse) other;
            return Intrinsics.areEqual(this.code, biliSubtitleResponse.code) && Intrinsics.areEqual(this.message, biliSubtitleResponse.message) && Intrinsics.areEqual(this.data, biliSubtitleResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSubtitleResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public BiliSubtitleResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliSubtitleData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ BiliSubtitleResponse(Integer num, String str, BiliSubtitleData biliSubtitleData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : biliSubtitleData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final BiliSubtitleData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSubtitleData;", "", "subtitles", "", "Lcom/cncverse/BilibiliProvider$BiliSubtitle;", "<init>", "(Ljava/util/List;)V", "getSubtitles", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSubtitleData {

        @JsonProperty("subtitles")
        @Nullable
        private final List<BiliSubtitle> subtitles;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliSubtitleData() {
            List list = null;
            this(list, 1, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliSubtitleData copy$default(BiliSubtitleData biliSubtitleData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = biliSubtitleData.subtitles;
            }
            return biliSubtitleData.copy(list);
        }

        @Nullable
        public final List<BiliSubtitle> component1() {
            return this.subtitles;
        }

        @NotNull
        public final BiliSubtitleData copy(@JsonProperty("subtitles") @Nullable List<BiliSubtitle> subtitles) {
            return new BiliSubtitleData(subtitles);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiliSubtitleData) && Intrinsics.areEqual(this.subtitles, ((BiliSubtitleData) other).subtitles);
        }

        public int hashCode() {
            if (this.subtitles == null) {
                return 0;
            }
            return this.subtitles.hashCode();
        }

        @NotNull
        public String toString() {
            return "BiliSubtitleData(subtitles=" + this.subtitles + ')';
        }

        public BiliSubtitleData(@JsonProperty("subtitles") @Nullable List<BiliSubtitle> list) {
            this.subtitles = list;
        }

        public /* synthetic */ BiliSubtitleData(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list);
        }

        @Nullable
        public final List<BiliSubtitle> getSubtitles() {
            return this.subtitles;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J-\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliSubtitle;", "", "url", "", "lang", "langDoc", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getUrl", "()Ljava/lang/String;", "getLang", "getLangDoc", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliSubtitle {

        @JsonProperty("lang")
        @Nullable
        private final String lang;

        @JsonProperty("lang_doc")
        @Nullable
        private final String langDoc;

        @JsonProperty("url")
        @Nullable
        private final String url;

        public BiliSubtitle() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliSubtitle copy$default(BiliSubtitle biliSubtitle, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliSubtitle.url;
            }
            if ((i & 2) != 0) {
                str2 = biliSubtitle.lang;
            }
            if ((i & 4) != 0) {
                str3 = biliSubtitle.langDoc;
            }
            return biliSubtitle.copy(str, str2, str3);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLang() {
            return this.lang;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLangDoc() {
            return this.langDoc;
        }

        @NotNull
        public final BiliSubtitle copy(@JsonProperty("url") @Nullable String url, @JsonProperty("lang") @Nullable String lang, @JsonProperty("lang_doc") @Nullable String langDoc) {
            return new BiliSubtitle(url, lang, langDoc);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliSubtitle)) {
                return false;
            }
            BiliSubtitle biliSubtitle = (BiliSubtitle) other;
            return Intrinsics.areEqual(this.url, biliSubtitle.url) && Intrinsics.areEqual(this.lang, biliSubtitle.lang) && Intrinsics.areEqual(this.langDoc, biliSubtitle.langDoc);
        }

        public int hashCode() {
            return ((((this.url == null ? 0 : this.url.hashCode()) * 31) + (this.lang == null ? 0 : this.lang.hashCode())) * 31) + (this.langDoc != null ? this.langDoc.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliSubtitle(url=" + this.url + ", lang=" + this.lang + ", langDoc=" + this.langDoc + ')';
        }

        public BiliSubtitle(@JsonProperty("url") @Nullable String url, @JsonProperty("lang") @Nullable String lang, @JsonProperty("lang_doc") @Nullable String langDoc) {
            this.url = url;
            this.lang = lang;
            this.langDoc = langDoc;
        }

        public /* synthetic */ BiliSubtitle(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3);
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final String getLang() {
            return this.lang;
        }

        @Nullable
        public final String getLangDoc() {
            return this.langDoc;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliPlayInfo;", "", "code", "", "data", "Lcom/cncverse/BilibiliProvider$BiliPlayInfoData;", "<init>", "(Ljava/lang/Integer;Lcom/cncverse/BilibiliProvider$BiliPlayInfoData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliPlayInfoData;", "component1", "component2", "copy", "(Ljava/lang/Integer;Lcom/cncverse/BilibiliProvider$BiliPlayInfoData;)Lcom/cncverse/BilibiliProvider$BiliPlayInfo;", "equals", "", "other", "hashCode", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliPlayInfo {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliPlayInfoData data;

        /* JADX WARN: Multi-variable type inference failed */
        public BiliPlayInfo() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ BiliPlayInfo copy$default(BiliPlayInfo biliPlayInfo, Integer num, BiliPlayInfoData biliPlayInfoData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliPlayInfo.code;
            }
            if ((i & 2) != 0) {
                biliPlayInfoData = biliPlayInfo.data;
            }
            return biliPlayInfo.copy(num, biliPlayInfoData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BiliPlayInfoData getData() {
            return this.data;
        }

        @NotNull
        public final BiliPlayInfo copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("data") @Nullable BiliPlayInfoData data) {
            return new BiliPlayInfo(code, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliPlayInfo)) {
                return false;
            }
            BiliPlayInfo biliPlayInfo = (BiliPlayInfo) other;
            return Intrinsics.areEqual(this.code, biliPlayInfo.code) && Intrinsics.areEqual(this.data, biliPlayInfo.data);
        }

        public int hashCode() {
            return ((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliPlayInfo(code=" + this.code + ", data=" + this.data + ')';
        }

        public BiliPlayInfo(@JsonProperty("code") @Nullable Integer code, @JsonProperty("data") @Nullable BiliPlayInfoData data) {
            this.code = code;
            this.data = data;
        }

        public /* synthetic */ BiliPlayInfo(Integer num, BiliPlayInfoData biliPlayInfoData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : biliPlayInfoData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final BiliPlayInfoData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliPlayInfoData;", "", "dash", "Lcom/cncverse/BilibiliProvider$BiliDash;", "<init>", "(Lcom/cncverse/BilibiliProvider$BiliDash;)V", "getDash", "()Lcom/cncverse/BilibiliProvider$BiliDash;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliPlayInfoData {

        @JsonProperty("dash")
        @Nullable
        private final BiliDash dash;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliPlayInfoData() {
            BiliDash biliDash = null;
            this(biliDash, 1, biliDash);
        }

        public static /* synthetic */ BiliPlayInfoData copy$default(BiliPlayInfoData biliPlayInfoData, BiliDash biliDash, int i, Object obj) {
            if ((i & 1) != 0) {
                biliDash = biliPlayInfoData.dash;
            }
            return biliPlayInfoData.copy(biliDash);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final BiliDash getDash() {
            return this.dash;
        }

        @NotNull
        public final BiliPlayInfoData copy(@JsonProperty("dash") @Nullable BiliDash dash) {
            return new BiliPlayInfoData(dash);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiliPlayInfoData) && Intrinsics.areEqual(this.dash, ((BiliPlayInfoData) other).dash);
        }

        public int hashCode() {
            if (this.dash == null) {
                return 0;
            }
            return this.dash.hashCode();
        }

        @NotNull
        public String toString() {
            return "BiliPlayInfoData(dash=" + this.dash + ')';
        }

        public BiliPlayInfoData(@JsonProperty("dash") @Nullable BiliDash dash) {
            this.dash = dash;
        }

        public /* synthetic */ BiliPlayInfoData(BiliDash biliDash, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : biliDash);
        }

        @Nullable
        public final BiliDash getDash() {
            return this.dash;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliDash;", "", "video", "", "Lcom/cncverse/BilibiliProvider$BiliDashVideo;", "audio", "Lcom/cncverse/BilibiliProvider$BiliDashAudio;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getVideo", "()Ljava/util/List;", "getAudio", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliDash {

        @JsonProperty("audio")
        @Nullable
        private final List<BiliDashAudio> audio;

        @JsonProperty("video")
        @Nullable
        private final List<BiliDashVideo> video;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliDash() {
            List list = null;
            this(list, list, 3, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliDash copy$default(BiliDash biliDash, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = biliDash.video;
            }
            if ((i & 2) != 0) {
                list2 = biliDash.audio;
            }
            return biliDash.copy(list, list2);
        }

        @Nullable
        public final List<BiliDashVideo> component1() {
            return this.video;
        }

        @Nullable
        public final List<BiliDashAudio> component2() {
            return this.audio;
        }

        @NotNull
        public final BiliDash copy(@JsonProperty("video") @Nullable List<BiliDashVideo> video, @JsonProperty("audio") @Nullable List<BiliDashAudio> audio) {
            return new BiliDash(video, audio);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliDash)) {
                return false;
            }
            BiliDash biliDash = (BiliDash) other;
            return Intrinsics.areEqual(this.video, biliDash.video) && Intrinsics.areEqual(this.audio, biliDash.audio);
        }

        public int hashCode() {
            return ((this.video == null ? 0 : this.video.hashCode()) * 31) + (this.audio != null ? this.audio.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliDash(video=" + this.video + ", audio=" + this.audio + ')';
        }

        public BiliDash(@JsonProperty("video") @Nullable List<BiliDashVideo> list, @JsonProperty("audio") @Nullable List<BiliDashAudio> list2) {
            this.video = list;
            this.audio = list2;
        }

        public /* synthetic */ BiliDash(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
        }

        @Nullable
        public final List<BiliDashVideo> getVideo() {
            return this.video;
        }

        @Nullable
        public final List<BiliDashAudio> getAudio() {
            return this.audio;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJJ\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliDashVideo;", "", "id", "", "baseUrl", "", "base_url", "bandwidth", "codecid", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBaseUrl", "()Ljava/lang/String;", "getBase_url", "getBandwidth", "getCodecid", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/cncverse/BilibiliProvider$BiliDashVideo;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliDashVideo {

        @JsonProperty("bandwidth")
        @Nullable
        private final Integer bandwidth;

        @JsonProperty("baseUrl")
        @Nullable
        private final String baseUrl;

        @JsonProperty("base_url")
        @Nullable
        private final String base_url;

        @JsonProperty("codecid")
        @Nullable
        private final Integer codecid;

        @JsonProperty("id")
        @Nullable
        private final Integer id;

        public BiliDashVideo() {
            this(null, null, null, null, null, 31, null);
        }

        public static /* synthetic */ BiliDashVideo copy$default(BiliDashVideo biliDashVideo, Integer num, String str, String str2, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliDashVideo.id;
            }
            if ((i & 2) != 0) {
                str = biliDashVideo.baseUrl;
            }
            if ((i & 4) != 0) {
                str2 = biliDashVideo.base_url;
            }
            if ((i & 8) != 0) {
                num2 = biliDashVideo.bandwidth;
            }
            if ((i & 16) != 0) {
                num3 = biliDashVideo.codecid;
            }
            Integer num4 = num3;
            String str3 = str2;
            return biliDashVideo.copy(num, str, str3, num2, num4);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getBaseUrl() {
            return this.baseUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getBase_url() {
            return this.base_url;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getCodecid() {
            return this.codecid;
        }

        @NotNull
        public final BiliDashVideo copy(@JsonProperty("id") @Nullable Integer id, @JsonProperty("baseUrl") @Nullable String baseUrl, @JsonProperty("base_url") @Nullable String base_url, @JsonProperty("bandwidth") @Nullable Integer bandwidth, @JsonProperty("codecid") @Nullable Integer codecid) {
            return new BiliDashVideo(id, baseUrl, base_url, bandwidth, codecid);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliDashVideo)) {
                return false;
            }
            BiliDashVideo biliDashVideo = (BiliDashVideo) other;
            return Intrinsics.areEqual(this.id, biliDashVideo.id) && Intrinsics.areEqual(this.baseUrl, biliDashVideo.baseUrl) && Intrinsics.areEqual(this.base_url, biliDashVideo.base_url) && Intrinsics.areEqual(this.bandwidth, biliDashVideo.bandwidth) && Intrinsics.areEqual(this.codecid, biliDashVideo.codecid);
        }

        public int hashCode() {
            return ((((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.baseUrl == null ? 0 : this.baseUrl.hashCode())) * 31) + (this.base_url == null ? 0 : this.base_url.hashCode())) * 31) + (this.bandwidth == null ? 0 : this.bandwidth.hashCode())) * 31) + (this.codecid != null ? this.codecid.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliDashVideo(id=" + this.id + ", baseUrl=" + this.baseUrl + ", base_url=" + this.base_url + ", bandwidth=" + this.bandwidth + ", codecid=" + this.codecid + ')';
        }

        public BiliDashVideo(@JsonProperty("id") @Nullable Integer id, @JsonProperty("baseUrl") @Nullable String baseUrl, @JsonProperty("base_url") @Nullable String base_url, @JsonProperty("bandwidth") @Nullable Integer bandwidth, @JsonProperty("codecid") @Nullable Integer codecid) {
            this.id = id;
            this.baseUrl = baseUrl;
            this.base_url = base_url;
            this.bandwidth = bandwidth;
            this.codecid = codecid;
        }

        public /* synthetic */ BiliDashVideo(Integer num, String str, String str2, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2, (i & 16) != 0 ? null : num3);
        }

        @Nullable
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        public final String getBaseUrl() {
            return this.baseUrl;
        }

        @Nullable
        public final String getBase_url() {
            return this.base_url;
        }

        @Nullable
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @Nullable
        public final Integer getCodecid() {
            return this.codecid;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ>\u0010\u0015\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliDashAudio;", "", "id", "", "baseUrl", "", "base_url", "bandwidth", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBaseUrl", "()Ljava/lang/String;", "getBase_url", "getBandwidth", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lcom/cncverse/BilibiliProvider$BiliDashAudio;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliDashAudio {

        @JsonProperty("bandwidth")
        @Nullable
        private final Integer bandwidth;

        @JsonProperty("baseUrl")
        @Nullable
        private final String baseUrl;

        @JsonProperty("base_url")
        @Nullable
        private final String base_url;

        @JsonProperty("id")
        @Nullable
        private final Integer id;

        public BiliDashAudio() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ BiliDashAudio copy$default(BiliDashAudio biliDashAudio, Integer num, String str, String str2, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliDashAudio.id;
            }
            if ((i & 2) != 0) {
                str = biliDashAudio.baseUrl;
            }
            if ((i & 4) != 0) {
                str2 = biliDashAudio.base_url;
            }
            if ((i & 8) != 0) {
                num2 = biliDashAudio.bandwidth;
            }
            return biliDashAudio.copy(num, str, str2, num2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getBaseUrl() {
            return this.baseUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getBase_url() {
            return this.base_url;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @NotNull
        public final BiliDashAudio copy(@JsonProperty("id") @Nullable Integer id, @JsonProperty("baseUrl") @Nullable String baseUrl, @JsonProperty("base_url") @Nullable String base_url, @JsonProperty("bandwidth") @Nullable Integer bandwidth) {
            return new BiliDashAudio(id, baseUrl, base_url, bandwidth);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliDashAudio)) {
                return false;
            }
            BiliDashAudio biliDashAudio = (BiliDashAudio) other;
            return Intrinsics.areEqual(this.id, biliDashAudio.id) && Intrinsics.areEqual(this.baseUrl, biliDashAudio.baseUrl) && Intrinsics.areEqual(this.base_url, biliDashAudio.base_url) && Intrinsics.areEqual(this.bandwidth, biliDashAudio.bandwidth);
        }

        public int hashCode() {
            return ((((((this.id == null ? 0 : this.id.hashCode()) * 31) + (this.baseUrl == null ? 0 : this.baseUrl.hashCode())) * 31) + (this.base_url == null ? 0 : this.base_url.hashCode())) * 31) + (this.bandwidth != null ? this.bandwidth.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliDashAudio(id=" + this.id + ", baseUrl=" + this.baseUrl + ", base_url=" + this.base_url + ", bandwidth=" + this.bandwidth + ')';
        }

        public BiliDashAudio(@JsonProperty("id") @Nullable Integer id, @JsonProperty("baseUrl") @Nullable String baseUrl, @JsonProperty("base_url") @Nullable String base_url, @JsonProperty("bandwidth") @Nullable Integer bandwidth) {
            this.id = id;
            this.baseUrl = baseUrl;
            this.base_url = base_url;
            this.bandwidth = bandwidth;
        }

        public /* synthetic */ BiliDashAudio(Integer num, String str, String str2, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : num2);
        }

        @Nullable
        public final Integer getId() {
            return this.id;
        }

        @Nullable
        public final String getBaseUrl() {
            return this.baseUrl;
        }

        @Nullable
        public final String getBase_url() {
            return this.base_url;
        }

        @Nullable
        public final Integer getBandwidth() {
            return this.bandwidth;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliTimelineResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/BilibiliProvider$BiliTimelineData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliTimelineData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliTimelineData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliTimelineData;)Lcom/cncverse/BilibiliProvider$BiliTimelineResponse;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliTimelineResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliTimelineData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public BiliTimelineResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliTimelineResponse copy$default(BiliTimelineResponse biliTimelineResponse, Integer num, String str, BiliTimelineData biliTimelineData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliTimelineResponse.code;
            }
            if ((i & 2) != 0) {
                str = biliTimelineResponse.message;
            }
            if ((i & 4) != 0) {
                biliTimelineData = biliTimelineResponse.data;
            }
            return biliTimelineResponse.copy(num, str, biliTimelineData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BiliTimelineData getData() {
            return this.data;
        }

        @NotNull
        public final BiliTimelineResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliTimelineData data) {
            return new BiliTimelineResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliTimelineResponse)) {
                return false;
            }
            BiliTimelineResponse biliTimelineResponse = (BiliTimelineResponse) other;
            return Intrinsics.areEqual(this.code, biliTimelineResponse.code) && Intrinsics.areEqual(this.message, biliTimelineResponse.message) && Intrinsics.areEqual(this.data, biliTimelineResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliTimelineResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public BiliTimelineResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliTimelineData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ BiliTimelineResponse(Integer num, String str, BiliTimelineData biliTimelineData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : biliTimelineData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final BiliTimelineData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliTimelineData;", "", "items", "", "Lcom/cncverse/BilibiliProvider$BiliTimelineDay;", "<init>", "(Ljava/util/List;)V", "getItems", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliTimelineData {

        @JsonProperty("items")
        @Nullable
        private final List<BiliTimelineDay> items;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliTimelineData() {
            List list = null;
            this(list, 1, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliTimelineData copy$default(BiliTimelineData biliTimelineData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = biliTimelineData.items;
            }
            return biliTimelineData.copy(list);
        }

        @Nullable
        public final List<BiliTimelineDay> component1() {
            return this.items;
        }

        @NotNull
        public final BiliTimelineData copy(@JsonProperty("items") @Nullable List<BiliTimelineDay> items) {
            return new BiliTimelineData(items);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiliTimelineData) && Intrinsics.areEqual(this.items, ((BiliTimelineData) other).items);
        }

        public int hashCode() {
            if (this.items == null) {
                return 0;
            }
            return this.items.hashCode();
        }

        @NotNull
        public String toString() {
            return "BiliTimelineData(items=" + this.items + ')';
        }

        public BiliTimelineData(@JsonProperty("items") @Nullable List<BiliTimelineDay> list) {
            this.items = list;
        }

        public /* synthetic */ BiliTimelineData(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list);
        }

        @Nullable
        public final List<BiliTimelineDay> getItems() {
            return this.items;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliTimelineDay;", "", "dayOfWeek", "", "dateText", "cards", "", "Lcom/cncverse/BilibiliProvider$BiliTimelineCard;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getDayOfWeek", "()Ljava/lang/String;", "getDateText", "getCards", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliTimelineDay {

        @JsonProperty("cards")
        @Nullable
        private final List<BiliTimelineCard> cards;

        @JsonProperty("date_text")
        @Nullable
        private final String dateText;

        @JsonProperty("day_of_week")
        @Nullable
        private final String dayOfWeek;

        public BiliTimelineDay() {
            this(null, null, null, 7, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliTimelineDay copy$default(BiliTimelineDay biliTimelineDay, String str, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliTimelineDay.dayOfWeek;
            }
            if ((i & 2) != 0) {
                str2 = biliTimelineDay.dateText;
            }
            if ((i & 4) != 0) {
                list = biliTimelineDay.cards;
            }
            return biliTimelineDay.copy(str, str2, list);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDayOfWeek() {
            return this.dayOfWeek;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getDateText() {
            return this.dateText;
        }

        @Nullable
        public final List<BiliTimelineCard> component3() {
            return this.cards;
        }

        @NotNull
        public final BiliTimelineDay copy(@JsonProperty("day_of_week") @Nullable String dayOfWeek, @JsonProperty("date_text") @Nullable String dateText, @JsonProperty("cards") @Nullable List<BiliTimelineCard> cards) {
            return new BiliTimelineDay(dayOfWeek, dateText, cards);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliTimelineDay)) {
                return false;
            }
            BiliTimelineDay biliTimelineDay = (BiliTimelineDay) other;
            return Intrinsics.areEqual(this.dayOfWeek, biliTimelineDay.dayOfWeek) && Intrinsics.areEqual(this.dateText, biliTimelineDay.dateText) && Intrinsics.areEqual(this.cards, biliTimelineDay.cards);
        }

        public int hashCode() {
            return ((((this.dayOfWeek == null ? 0 : this.dayOfWeek.hashCode()) * 31) + (this.dateText == null ? 0 : this.dateText.hashCode())) * 31) + (this.cards != null ? this.cards.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliTimelineDay(dayOfWeek=" + this.dayOfWeek + ", dateText=" + this.dateText + ", cards=" + this.cards + ')';
        }

        public BiliTimelineDay(@JsonProperty("day_of_week") @Nullable String dayOfWeek, @JsonProperty("date_text") @Nullable String dateText, @JsonProperty("cards") @Nullable List<BiliTimelineCard> list) {
            this.dayOfWeek = dayOfWeek;
            this.dateText = dateText;
            this.cards = list;
        }

        public /* synthetic */ BiliTimelineDay(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : list);
        }

        @Nullable
        public final String getDayOfWeek() {
            return this.dayOfWeek;
        }

        @Nullable
        public final String getDateText() {
            return this.dateText;
        }

        @Nullable
        public final List<BiliTimelineCard> getCards() {
            return this.cards;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0003Jc\u0010\u001d\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nHÆ\u0001J\u0014\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliTimelineCard;", "", "title", "", "seasonId", "episodeId", "cover", "view", "indexShow", "styleList", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getTitle", "()Ljava/lang/String;", "getSeasonId", "getEpisodeId", "getCover", "getView", "getIndexShow", "getStyleList", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliTimelineCard {

        @JsonProperty("cover")
        @Nullable
        private final String cover;

        @JsonProperty("episode_id")
        @Nullable
        private final String episodeId;

        @JsonProperty("index_show")
        @Nullable
        private final String indexShow;

        @JsonProperty("season_id")
        @Nullable
        private final String seasonId;

        @JsonProperty("style_list")
        @Nullable
        private final List<String> styleList;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("view")
        @Nullable
        private final String view;

        public BiliTimelineCard() {
            this(null, null, null, null, null, null, null, 127, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliTimelineCard copy$default(BiliTimelineCard biliTimelineCard, String str, String str2, String str3, String str4, String str5, String str6, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliTimelineCard.title;
            }
            if ((i & 2) != 0) {
                str2 = biliTimelineCard.seasonId;
            }
            if ((i & 4) != 0) {
                str3 = biliTimelineCard.episodeId;
            }
            if ((i & 8) != 0) {
                str4 = biliTimelineCard.cover;
            }
            if ((i & 16) != 0) {
                str5 = biliTimelineCard.view;
            }
            if ((i & 32) != 0) {
                str6 = biliTimelineCard.indexShow;
            }
            if ((i & 64) != 0) {
                list = biliTimelineCard.styleList;
            }
            String str7 = str6;
            List list2 = list;
            String str8 = str5;
            String str9 = str3;
            return biliTimelineCard.copy(str, str2, str9, str4, str8, str7, list2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEpisodeId() {
            return this.episodeId;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getView() {
            return this.view;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getIndexShow() {
            return this.indexShow;
        }

        @Nullable
        public final List<String> component7() {
            return this.styleList;
        }

        @NotNull
        public final BiliTimelineCard copy(@JsonProperty("title") @Nullable String title, @JsonProperty("season_id") @Nullable String seasonId, @JsonProperty("episode_id") @Nullable String episodeId, @JsonProperty("cover") @Nullable String cover, @JsonProperty("view") @Nullable String view, @JsonProperty("index_show") @Nullable String indexShow, @JsonProperty("style_list") @Nullable List<String> styleList) {
            return new BiliTimelineCard(title, seasonId, episodeId, cover, view, indexShow, styleList);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliTimelineCard)) {
                return false;
            }
            BiliTimelineCard biliTimelineCard = (BiliTimelineCard) other;
            return Intrinsics.areEqual(this.title, biliTimelineCard.title) && Intrinsics.areEqual(this.seasonId, biliTimelineCard.seasonId) && Intrinsics.areEqual(this.episodeId, biliTimelineCard.episodeId) && Intrinsics.areEqual(this.cover, biliTimelineCard.cover) && Intrinsics.areEqual(this.view, biliTimelineCard.view) && Intrinsics.areEqual(this.indexShow, biliTimelineCard.indexShow) && Intrinsics.areEqual(this.styleList, biliTimelineCard.styleList);
        }

        public int hashCode() {
            return ((((((((((((this.title == null ? 0 : this.title.hashCode()) * 31) + (this.seasonId == null ? 0 : this.seasonId.hashCode())) * 31) + (this.episodeId == null ? 0 : this.episodeId.hashCode())) * 31) + (this.cover == null ? 0 : this.cover.hashCode())) * 31) + (this.view == null ? 0 : this.view.hashCode())) * 31) + (this.indexShow == null ? 0 : this.indexShow.hashCode())) * 31) + (this.styleList != null ? this.styleList.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliTimelineCard(title=" + this.title + ", seasonId=" + this.seasonId + ", episodeId=" + this.episodeId + ", cover=" + this.cover + ", view=" + this.view + ", indexShow=" + this.indexShow + ", styleList=" + this.styleList + ')';
        }

        public BiliTimelineCard(@JsonProperty("title") @Nullable String title, @JsonProperty("season_id") @Nullable String seasonId, @JsonProperty("episode_id") @Nullable String episodeId, @JsonProperty("cover") @Nullable String cover, @JsonProperty("view") @Nullable String view, @JsonProperty("index_show") @Nullable String indexShow, @JsonProperty("style_list") @Nullable List<String> list) {
            this.title = title;
            this.seasonId = seasonId;
            this.episodeId = episodeId;
            this.cover = cover;
            this.view = view;
            this.indexShow = indexShow;
            this.styleList = list;
        }

        public /* synthetic */ BiliTimelineCard(String str, String str2, String str3, String str4, String str5, String str6, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : list);
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final String getSeasonId() {
            return this.seasonId;
        }

        @Nullable
        public final String getEpisodeId() {
            return this.episodeId;
        }

        @Nullable
        public final String getCover() {
            return this.cover;
        }

        @Nullable
        public final String getView() {
            return this.view;
        }

        @Nullable
        public final String getIndexShow() {
            return this.indexShow;
        }

        @Nullable
        public final List<String> getStyleList() {
            return this.styleList;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliPlayurlResponse;", "", "code", "", "message", "", "data", "Lcom/cncverse/BilibiliProvider$BiliPlayurlData;", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliPlayurlData;)V", "getCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/BilibiliProvider$BiliPlayurlData;", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/cncverse/BilibiliProvider$BiliPlayurlData;)Lcom/cncverse/BilibiliProvider$BiliPlayurlResponse;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliPlayurlResponse {

        @JsonProperty("code")
        @Nullable
        private final Integer code;

        @JsonProperty("data")
        @Nullable
        private final BiliPlayurlData data;

        @JsonProperty("message")
        @Nullable
        private final String message;

        public BiliPlayurlResponse() {
            this(null, null, null, 7, null);
        }

        public static /* synthetic */ BiliPlayurlResponse copy$default(BiliPlayurlResponse biliPlayurlResponse, Integer num, String str, BiliPlayurlData biliPlayurlData, int i, Object obj) {
            if ((i & 1) != 0) {
                num = biliPlayurlResponse.code;
            }
            if ((i & 2) != 0) {
                str = biliPlayurlResponse.message;
            }
            if ((i & 4) != 0) {
                biliPlayurlData = biliPlayurlResponse.data;
            }
            return biliPlayurlResponse.copy(num, str, biliPlayurlData);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BiliPlayurlData getData() {
            return this.data;
        }

        @NotNull
        public final BiliPlayurlResponse copy(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliPlayurlData data) {
            return new BiliPlayurlResponse(code, message, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliPlayurlResponse)) {
                return false;
            }
            BiliPlayurlResponse biliPlayurlResponse = (BiliPlayurlResponse) other;
            return Intrinsics.areEqual(this.code, biliPlayurlResponse.code) && Intrinsics.areEqual(this.message, biliPlayurlResponse.message) && Intrinsics.areEqual(this.data, biliPlayurlResponse.data);
        }

        public int hashCode() {
            return ((((this.code == null ? 0 : this.code.hashCode()) * 31) + (this.message == null ? 0 : this.message.hashCode())) * 31) + (this.data != null ? this.data.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliPlayurlResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
        }

        public BiliPlayurlResponse(@JsonProperty("code") @Nullable Integer code, @JsonProperty("message") @Nullable String message, @JsonProperty("data") @Nullable BiliPlayurlData data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public /* synthetic */ BiliPlayurlResponse(Integer num, String str, BiliPlayurlData biliPlayurlData, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : biliPlayurlData);
        }

        @Nullable
        public final Integer getCode() {
            return this.code;
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        @Nullable
        public final BiliPlayurlData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliPlayurlData;", "", "playurl", "Lcom/cncverse/BilibiliProvider$BiliPlayurlInfo;", "<init>", "(Lcom/cncverse/BilibiliProvider$BiliPlayurlInfo;)V", "getPlayurl", "()Lcom/cncverse/BilibiliProvider$BiliPlayurlInfo;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliPlayurlData {

        @JsonProperty("playurl")
        @Nullable
        private final BiliPlayurlInfo playurl;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliPlayurlData() {
            BiliPlayurlInfo biliPlayurlInfo = null;
            this(biliPlayurlInfo, 1, biliPlayurlInfo);
        }

        public static /* synthetic */ BiliPlayurlData copy$default(BiliPlayurlData biliPlayurlData, BiliPlayurlInfo biliPlayurlInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                biliPlayurlInfo = biliPlayurlData.playurl;
            }
            return biliPlayurlData.copy(biliPlayurlInfo);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final BiliPlayurlInfo getPlayurl() {
            return this.playurl;
        }

        @NotNull
        public final BiliPlayurlData copy(@JsonProperty("playurl") @Nullable BiliPlayurlInfo playurl) {
            return new BiliPlayurlData(playurl);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof BiliPlayurlData) && Intrinsics.areEqual(this.playurl, ((BiliPlayurlData) other).playurl);
        }

        public int hashCode() {
            if (this.playurl == null) {
                return 0;
            }
            return this.playurl.hashCode();
        }

        @NotNull
        public String toString() {
            return "BiliPlayurlData(playurl=" + this.playurl + ')';
        }

        public BiliPlayurlData(@JsonProperty("playurl") @Nullable BiliPlayurlInfo playurl) {
            this.playurl = playurl;
        }

        public /* synthetic */ BiliPlayurlData(BiliPlayurlInfo biliPlayurlInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : biliPlayurlInfo);
        }

        @Nullable
        public final BiliPlayurlInfo getPlayurl() {
            return this.playurl;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\u0010\b\u0003\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0003\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u001e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliPlayurlInfo;", "", "video", "", "Lcom/cncverse/BilibiliProvider$BiliVideoStream;", "audioResource", "Lcom/cncverse/BilibiliProvider$BiliAudioResource;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getVideo", "()Ljava/util/List;", "getAudioResource", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliPlayurlInfo {

        @JsonProperty("audio_resource")
        @Nullable
        private final List<BiliAudioResource> audioResource;

        @JsonProperty("video")
        @Nullable
        private final List<BiliVideoStream> video;

        /* JADX WARN: Illegal instructions before constructor call */
        public BiliPlayurlInfo() {
            List list = null;
            this(list, list, 3, list);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BiliPlayurlInfo copy$default(BiliPlayurlInfo biliPlayurlInfo, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = biliPlayurlInfo.video;
            }
            if ((i & 2) != 0) {
                list2 = biliPlayurlInfo.audioResource;
            }
            return biliPlayurlInfo.copy(list, list2);
        }

        @Nullable
        public final List<BiliVideoStream> component1() {
            return this.video;
        }

        @Nullable
        public final List<BiliAudioResource> component2() {
            return this.audioResource;
        }

        @NotNull
        public final BiliPlayurlInfo copy(@JsonProperty("video") @Nullable List<BiliVideoStream> video, @JsonProperty("audio_resource") @Nullable List<BiliAudioResource> audioResource) {
            return new BiliPlayurlInfo(video, audioResource);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliPlayurlInfo)) {
                return false;
            }
            BiliPlayurlInfo biliPlayurlInfo = (BiliPlayurlInfo) other;
            return Intrinsics.areEqual(this.video, biliPlayurlInfo.video) && Intrinsics.areEqual(this.audioResource, biliPlayurlInfo.audioResource);
        }

        public int hashCode() {
            return ((this.video == null ? 0 : this.video.hashCode()) * 31) + (this.audioResource != null ? this.audioResource.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliPlayurlInfo(video=" + this.video + ", audioResource=" + this.audioResource + ')';
        }

        public BiliPlayurlInfo(@JsonProperty("video") @Nullable List<BiliVideoStream> list, @JsonProperty("audio_resource") @Nullable List<BiliAudioResource> list2) {
            this.video = list;
            this.audioResource = list2;
        }

        public /* synthetic */ BiliPlayurlInfo(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : list2);
        }

        @Nullable
        public final List<BiliVideoStream> getVideo() {
            return this.video;
        }

        @Nullable
        public final List<BiliAudioResource> getAudioResource() {
            return this.audioResource;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliVideoStream;", "", "videoResource", "Lcom/cncverse/BilibiliProvider$BiliVideoResource;", "streamInfo", "Lcom/cncverse/BilibiliProvider$BiliStreamInfo;", "<init>", "(Lcom/cncverse/BilibiliProvider$BiliVideoResource;Lcom/cncverse/BilibiliProvider$BiliStreamInfo;)V", "getVideoResource", "()Lcom/cncverse/BilibiliProvider$BiliVideoResource;", "getStreamInfo", "()Lcom/cncverse/BilibiliProvider$BiliStreamInfo;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliVideoStream {

        @JsonProperty("stream_info")
        @Nullable
        private final BiliStreamInfo streamInfo;

        @JsonProperty("video_resource")
        @Nullable
        private final BiliVideoResource videoResource;

        /* JADX WARN: Multi-variable type inference failed */
        public BiliVideoStream() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ BiliVideoStream copy$default(BiliVideoStream biliVideoStream, BiliVideoResource biliVideoResource, BiliStreamInfo biliStreamInfo, int i, Object obj) {
            if ((i & 1) != 0) {
                biliVideoResource = biliVideoStream.videoResource;
            }
            if ((i & 2) != 0) {
                biliStreamInfo = biliVideoStream.streamInfo;
            }
            return biliVideoStream.copy(biliVideoResource, biliStreamInfo);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final BiliVideoResource getVideoResource() {
            return this.videoResource;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final BiliStreamInfo getStreamInfo() {
            return this.streamInfo;
        }

        @NotNull
        public final BiliVideoStream copy(@JsonProperty("video_resource") @Nullable BiliVideoResource videoResource, @JsonProperty("stream_info") @Nullable BiliStreamInfo streamInfo) {
            return new BiliVideoStream(videoResource, streamInfo);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliVideoStream)) {
                return false;
            }
            BiliVideoStream biliVideoStream = (BiliVideoStream) other;
            return Intrinsics.areEqual(this.videoResource, biliVideoStream.videoResource) && Intrinsics.areEqual(this.streamInfo, biliVideoStream.streamInfo);
        }

        public int hashCode() {
            return ((this.videoResource == null ? 0 : this.videoResource.hashCode()) * 31) + (this.streamInfo != null ? this.streamInfo.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliVideoStream(videoResource=" + this.videoResource + ", streamInfo=" + this.streamInfo + ')';
        }

        public BiliVideoStream(@JsonProperty("video_resource") @Nullable BiliVideoResource videoResource, @JsonProperty("stream_info") @Nullable BiliStreamInfo streamInfo) {
            this.videoResource = videoResource;
            this.streamInfo = streamInfo;
        }

        public /* synthetic */ BiliVideoStream(BiliVideoResource biliVideoResource, BiliStreamInfo biliStreamInfo, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : biliVideoResource, (i & 2) != 0 ? null : biliStreamInfo);
        }

        @Nullable
        public final BiliVideoResource getVideoResource() {
            return this.videoResource;
        }

        @Nullable
        public final BiliStreamInfo getStreamInfo() {
            return this.streamInfo;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0016JV\u0010\u001e\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0013\u0010\u0010R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliVideoResource;", "", "url", "", "width", "", "height", "bandwidth", "codecs", "size", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V", "getUrl", "()Ljava/lang/String;", "getWidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "getBandwidth", "getCodecs", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)Lcom/cncverse/BilibiliProvider$BiliVideoResource;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliVideoResource {

        @JsonProperty("bandwidth")
        @Nullable
        private final Integer bandwidth;

        @JsonProperty("codecs")
        @Nullable
        private final String codecs;

        @JsonProperty("height")
        @Nullable
        private final Integer height;

        @JsonProperty("size")
        @Nullable
        private final Long size;

        @JsonProperty("url")
        @Nullable
        private final String url;

        @JsonProperty("width")
        @Nullable
        private final Integer width;

        public BiliVideoResource() {
            this(null, null, null, null, null, null, 63, null);
        }

        public static /* synthetic */ BiliVideoResource copy$default(BiliVideoResource biliVideoResource, String str, Integer num, Integer num2, Integer num3, String str2, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliVideoResource.url;
            }
            if ((i & 2) != 0) {
                num = biliVideoResource.width;
            }
            if ((i & 4) != 0) {
                num2 = biliVideoResource.height;
            }
            if ((i & 8) != 0) {
                num3 = biliVideoResource.bandwidth;
            }
            if ((i & 16) != 0) {
                str2 = biliVideoResource.codecs;
            }
            if ((i & 32) != 0) {
                l = biliVideoResource.size;
            }
            String str3 = str2;
            Long l2 = l;
            return biliVideoResource.copy(str, num, num2, num3, str3, l2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getWidth() {
            return this.width;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getHeight() {
            return this.height;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getCodecs() {
            return this.codecs;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Long getSize() {
            return this.size;
        }

        @NotNull
        public final BiliVideoResource copy(@JsonProperty("url") @Nullable String url, @JsonProperty("width") @Nullable Integer width, @JsonProperty("height") @Nullable Integer height, @JsonProperty("bandwidth") @Nullable Integer bandwidth, @JsonProperty("codecs") @Nullable String codecs, @JsonProperty("size") @Nullable Long size) {
            return new BiliVideoResource(url, width, height, bandwidth, codecs, size);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliVideoResource)) {
                return false;
            }
            BiliVideoResource biliVideoResource = (BiliVideoResource) other;
            return Intrinsics.areEqual(this.url, biliVideoResource.url) && Intrinsics.areEqual(this.width, biliVideoResource.width) && Intrinsics.areEqual(this.height, biliVideoResource.height) && Intrinsics.areEqual(this.bandwidth, biliVideoResource.bandwidth) && Intrinsics.areEqual(this.codecs, biliVideoResource.codecs) && Intrinsics.areEqual(this.size, biliVideoResource.size);
        }

        public int hashCode() {
            return ((((((((((this.url == null ? 0 : this.url.hashCode()) * 31) + (this.width == null ? 0 : this.width.hashCode())) * 31) + (this.height == null ? 0 : this.height.hashCode())) * 31) + (this.bandwidth == null ? 0 : this.bandwidth.hashCode())) * 31) + (this.codecs == null ? 0 : this.codecs.hashCode())) * 31) + (this.size != null ? this.size.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliVideoResource(url=" + this.url + ", width=" + this.width + ", height=" + this.height + ", bandwidth=" + this.bandwidth + ", codecs=" + this.codecs + ", size=" + this.size + ')';
        }

        public BiliVideoResource(@JsonProperty("url") @Nullable String url, @JsonProperty("width") @Nullable Integer width, @JsonProperty("height") @Nullable Integer height, @JsonProperty("bandwidth") @Nullable Integer bandwidth, @JsonProperty("codecs") @Nullable String codecs, @JsonProperty("size") @Nullable Long size) {
            this.url = url;
            this.width = width;
            this.height = height;
            this.bandwidth = bandwidth;
            this.codecs = codecs;
            this.size = size;
        }

        public /* synthetic */ BiliVideoResource(String str, Integer num, Integer num2, Integer num3, String str2, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : str2, (i & 32) != 0 ? null : l);
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Integer getWidth() {
            return this.width;
        }

        @Nullable
        public final Integer getHeight() {
            return this.height;
        }

        @Nullable
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @Nullable
        public final String getCodecs() {
            return this.codecs;
        }

        @Nullable
        public final Long getSize() {
            return this.size;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliStreamInfo;", "", "descWords", "", "quality", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getDescWords", "()Ljava/lang/String;", "getQuality", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/cncverse/BilibiliProvider$BiliStreamInfo;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliStreamInfo {

        @JsonProperty("desc_words")
        @Nullable
        private final String descWords;

        @JsonProperty("quality")
        @Nullable
        private final Integer quality;

        /* JADX WARN: Multi-variable type inference failed */
        public BiliStreamInfo() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ BiliStreamInfo copy$default(BiliStreamInfo biliStreamInfo, String str, Integer num, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliStreamInfo.descWords;
            }
            if ((i & 2) != 0) {
                num = biliStreamInfo.quality;
            }
            return biliStreamInfo.copy(str, num);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getDescWords() {
            return this.descWords;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getQuality() {
            return this.quality;
        }

        @NotNull
        public final BiliStreamInfo copy(@JsonProperty("desc_words") @Nullable String descWords, @JsonProperty("quality") @Nullable Integer quality) {
            return new BiliStreamInfo(descWords, quality);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliStreamInfo)) {
                return false;
            }
            BiliStreamInfo biliStreamInfo = (BiliStreamInfo) other;
            return Intrinsics.areEqual(this.descWords, biliStreamInfo.descWords) && Intrinsics.areEqual(this.quality, biliStreamInfo.quality);
        }

        public int hashCode() {
            return ((this.descWords == null ? 0 : this.descWords.hashCode()) * 31) + (this.quality != null ? this.quality.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliStreamInfo(descWords=" + this.descWords + ", quality=" + this.quality + ')';
        }

        public BiliStreamInfo(@JsonProperty("desc_words") @Nullable String descWords, @JsonProperty("quality") @Nullable Integer quality) {
            this.descWords = descWords;
            this.quality = quality;
        }

        public /* synthetic */ BiliStreamInfo(String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num);
        }

        @Nullable
        public final String getDescWords() {
            return this.descWords;
        }

        @Nullable
        public final Integer getQuality() {
            return this.quality;
        }
    }

    /* JADX INFO: compiled from: BilibiliProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0012J>\u0010\u0018\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u001a\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/cncverse/BilibiliProvider$BiliAudioResource;", "", "url", "", "bandwidth", "", "codecs", "size", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V", "getUrl", "()Ljava/lang/String;", "getBandwidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCodecs", "getSize", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)Lcom/cncverse/BilibiliProvider$BiliAudioResource;", "equals", "", "other", "hashCode", "toString", "BilibiliProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class BiliAudioResource {

        @JsonProperty("bandwidth")
        @Nullable
        private final Integer bandwidth;

        @JsonProperty("codecs")
        @Nullable
        private final String codecs;

        @JsonProperty("size")
        @Nullable
        private final Long size;

        @JsonProperty("url")
        @Nullable
        private final String url;

        public BiliAudioResource() {
            this(null, null, null, null, 15, null);
        }

        public static /* synthetic */ BiliAudioResource copy$default(BiliAudioResource biliAudioResource, String str, Integer num, String str2, Long l, int i, Object obj) {
            if ((i & 1) != 0) {
                str = biliAudioResource.url;
            }
            if ((i & 2) != 0) {
                num = biliAudioResource.bandwidth;
            }
            if ((i & 4) != 0) {
                str2 = biliAudioResource.codecs;
            }
            if ((i & 8) != 0) {
                l = biliAudioResource.size;
            }
            return biliAudioResource.copy(str, num, str2, l);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCodecs() {
            return this.codecs;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Long getSize() {
            return this.size;
        }

        @NotNull
        public final BiliAudioResource copy(@JsonProperty("url") @Nullable String url, @JsonProperty("bandwidth") @Nullable Integer bandwidth, @JsonProperty("codecs") @Nullable String codecs, @JsonProperty("size") @Nullable Long size) {
            return new BiliAudioResource(url, bandwidth, codecs, size);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BiliAudioResource)) {
                return false;
            }
            BiliAudioResource biliAudioResource = (BiliAudioResource) other;
            return Intrinsics.areEqual(this.url, biliAudioResource.url) && Intrinsics.areEqual(this.bandwidth, biliAudioResource.bandwidth) && Intrinsics.areEqual(this.codecs, biliAudioResource.codecs) && Intrinsics.areEqual(this.size, biliAudioResource.size);
        }

        public int hashCode() {
            return ((((((this.url == null ? 0 : this.url.hashCode()) * 31) + (this.bandwidth == null ? 0 : this.bandwidth.hashCode())) * 31) + (this.codecs == null ? 0 : this.codecs.hashCode())) * 31) + (this.size != null ? this.size.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "BiliAudioResource(url=" + this.url + ", bandwidth=" + this.bandwidth + ", codecs=" + this.codecs + ", size=" + this.size + ')';
        }

        public BiliAudioResource(@JsonProperty("url") @Nullable String url, @JsonProperty("bandwidth") @Nullable Integer bandwidth, @JsonProperty("codecs") @Nullable String codecs, @JsonProperty("size") @Nullable Long size) {
            this.url = url;
            this.bandwidth = bandwidth;
            this.codecs = codecs;
            this.size = size;
        }

        public /* synthetic */ BiliAudioResource(String str, Integer num, String str2, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : l);
        }

        @Nullable
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        public final Integer getBandwidth() {
            return this.bandwidth;
        }

        @Nullable
        public final String getCodecs() {
            return this.codecs;
        }

        @Nullable
        public final Long getSize() {
            return this.size;
        }
    }
}
