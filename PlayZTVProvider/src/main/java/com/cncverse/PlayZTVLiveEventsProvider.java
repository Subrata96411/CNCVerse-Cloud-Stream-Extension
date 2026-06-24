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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lagradost.cloudstream3.LiveSearchResponse;
import com.lagradost.cloudstream3.LiveStreamLoadResponse;
import com.lagradost.cloudstream3.LoadResponse;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MainAPIKt;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.mvvm.ArchComponentExtKt;
import com.lagradost.cloudstream3.ui.settings.Globals;
import com.lagradost.cloudstream3.utils.AppUtils;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KType;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nPlayZTVLiveEventsProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlayZTVLiveEventsProvider.kt\ncom/cncverse/PlayZTVLiveEventsProvider\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 6 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 7 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n*L\n1#1,623:1\n1#2:624\n1#2:657\n1#2:676\n1#2:707\n1512#3:625\n1538#3,3:626\n1541#3,3:636\n1080#3:642\n1586#3:643\n1661#3,3:644\n1068#3:648\n777#3:649\n873#3,2:650\n1586#3:652\n1661#3,3:653\n1915#3,2:694\n1642#3,10:696\n1915#3:706\n1916#3:708\n1652#3:709\n1586#3:710\n1661#3,3:711\n383#4,7:629\n129#5:639\n158#5,2:640\n160#5:647\n63#6:656\n64#6,15:658\n63#6:675\n64#6,15:677\n50#7:673\n43#7:674\n50#7:692\n43#7:693\n*S KotlinDebug\n*F\n+ 1 PlayZTVLiveEventsProvider.kt\ncom/cncverse/PlayZTVLiveEventsProvider\n*L\n235#1:657\n522#1:676\n609#1:707\n168#1:625\n168#1:626,3\n168#1:636,3\n179#1:642\n180#1:643\n180#1:644,3\n196#1:648\n211#1:649\n211#1:650,2\n217#1:652\n217#1:653,3\n525#1:694,2\n609#1:696,10\n609#1:706\n609#1:708\n609#1:709\n619#1:710\n619#1:711,3\n168#1:629,7\n171#1:639\n171#1:640,2\n171#1:647\n235#1:656\n235#1:658,15\n522#1:675\n522#1:677,15\n235#1:673\n235#1:674\n522#1:692\n522#1:693\n*E\n"})
public final class PlayZTVLiveEventsProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String mainUrl = "https://adsflw.xyz";

    @NotNull
    private String name = "⚡PlayZTV Live Events";

    @NotNull
    private String lang = "hi";
    private final boolean hasMainPage = true;
    private final boolean hasChromecastSupport = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(TvType.Live);

    @NotNull
    private final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

    /* JADX INFO: renamed from: com.cncverse.PlayZTVLiveEventsProvider$getMainPage$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVLiveEventsProvider", f = "PlayZTVLiveEventsProvider.kt", i = {0, 0}, l = {167}, m = "getMainPage", n = {"request", "page"}, nl = {168}, s = {"L$0", "I$0"}, v = 2)
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
            return PlayZTVLiveEventsProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVLiveEventsProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVLiveEventsProvider", f = "PlayZTVLiveEventsProvider.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {523, 539, 548, 562}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "loadData", "isCasting", "data", "subtitleCallback", "callback", "loadData", "streams", "$this$forEach$iv", "element$iv", "stream", "streamLink", "serverName", "url", "headers", "kidBase64", "keyBase64", "drmInfo", "isCasting", "$i$f$forEach", "$i$a$-forEach-PlayZTVLiveEventsProvider$loadLinks$3", "data", "subtitleCallback", "callback", "loadData", "streams", "$this$forEach$iv", "element$iv", "stream", "streamLink", "serverName", "url", "headers", "drmInfo", "isCasting", "$i$f$forEach", "$i$a$-forEach-PlayZTVLiveEventsProvider$loadLinks$3", "data", "subtitleCallback", "callback", "loadData", "streams", "$this$forEach$iv", "element$iv", "stream", "streamLink", "serverName", "url", "headers", "finalHeaders", "isCasting", "$i$f$forEach", "$i$a$-forEach-PlayZTVLiveEventsProvider$loadLinks$3"}, nl = {524, 538, 547, 561}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "Z$0", "I$0", "I$1"}, v = 2)
    static final class C00041 extends ContinuationImpl {
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
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlayZTVLiveEventsProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVLiveEventsProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVLiveEventsProvider", f = "PlayZTVLiveEventsProvider.kt", i = {0}, l = {210}, m = "search", n = {"query"}, nl = {211}, s = {"L$0"}, v = 2)
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
            return PlayZTVLiveEventsProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/PlayZTVLiveEventsProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "PlayZTVProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return PlayZTVLiveEventsProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            PlayZTVLiveEventsProvider.context = context;
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

    public boolean getHasChromecastSupport() {
        return this.hasChromecastSupport;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    private final String createDisplayTitle(PlayZLiveEventData event) {
        PlayZLiveEventInfo info = event.getEventInfo();
        if (info == null) {
            return event.getTitle();
        }
        String teamA = info.getTeamA();
        if (!(teamA == null || StringsKt.isBlank(teamA))) {
            String teamB = info.getTeamB();
            if (!(teamB == null || StringsKt.isBlank(teamB)) && !Intrinsics.areEqual(info.getTeamA(), info.getTeamB())) {
                return info.getTeamA() + " vs " + info.getTeamB();
            }
        }
        String teamA2 = info.getTeamA();
        return teamA2 == null ? event.getTitle() : teamA2;
    }

    private final String getEventStatus(PlayZLiveEventData event) {
        Date date;
        Date date2;
        PlayZLiveEventInfo info = event.getEventInfo();
        if (info == null) {
            return "";
        }
        long now = System.currentTimeMillis();
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.US);
            String it = info.getStartTime();
            Long end = null;
            Long start = (it == null || (date2 = fmt.parse(it)) == null) ? null : Long.valueOf(date2.getTime());
            String it2 = info.getEndTime();
            if (it2 != null && (date = fmt.parse(it2)) != null) {
                end = Long.valueOf(date.getTime());
            }
            return (end == null || now < end.longValue()) ? (start == null || now < start.longValue()) ? start != null ? now < start.longValue() ? "🔜" : "" : "" : "🔴" : "✅";
        } catch (Exception e) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isEventLive(PlayZLiveEventData event) {
        Date date;
        Date date2;
        PlayZLiveEventInfo info = event.getEventInfo();
        if (info == null) {
            return false;
        }
        long now = System.currentTimeMillis();
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.US);
            String it = info.getStartTime();
            Long end = null;
            Long start = (it == null || (date2 = fmt.parse(it)) == null) ? null : Long.valueOf(date2.getTime());
            String it2 = info.getEndTime();
            if (it2 != null && (date = fmt.parse(it2)) != null) {
                end = Long.valueOf(date.getTime());
            }
            if ((end == null || now < end.longValue()) && start != null) {
                return now >= start.longValue();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private final boolean isEventEnded(PlayZLiveEventData event) {
        Date date;
        PlayZLiveEventInfo info = event.getEventInfo();
        if (info == null) {
            return false;
        }
        long now = System.currentTimeMillis();
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.US);
            String it = info.getEndTime();
            Long end = null;
            if (it != null && (date = fmt.parse(it)) != null) {
                end = Long.valueOf(date.getTime());
            }
            if (end != null) {
                return now >= end.longValue();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String generateMatchCardUrl(com.cncverse.PlayZLiveEventData r19) {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVLiveEventsProvider.generateMatchCardUrl(com.cncverse.PlayZLiveEventData):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String generateMatchCardUrl$lambda$0(String it) {
        return URLEncoder.encode(it, "UTF-8");
    }

    /* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\fHÆ\u0003JM\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006%"}, d2 = {"Lcom/cncverse/PlayZTVLiveEventsProvider$LiveEventLoadData;", "", "eventId", "", "title", "", "poster", "slug", "formats", "", "Lcom/cncverse/PlayZLiveEventFormat;", "eventInfo", "Lcom/cncverse/PlayZLiveEventInfo;", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lcom/cncverse/PlayZLiveEventInfo;)V", "getEventId", "()I", "getTitle", "()Ljava/lang/String;", "getPoster", "getSlug", "getFormats", "()Ljava/util/List;", "getEventInfo", "()Lcom/cncverse/PlayZLiveEventInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "PlayZTVProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LiveEventLoadData {
        private final int eventId;

        @Nullable
        private final PlayZLiveEventInfo eventInfo;

        @NotNull
        private final List<PlayZLiveEventFormat> formats;

        @NotNull
        private final String poster;

        @NotNull
        private final String slug;

        @NotNull
        private final String title;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ LiveEventLoadData copy$default(LiveEventLoadData liveEventLoadData, int i, String str, String str2, String str3, List list, PlayZLiveEventInfo playZLiveEventInfo, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = liveEventLoadData.eventId;
            }
            if ((i2 & 2) != 0) {
                str = liveEventLoadData.title;
            }
            if ((i2 & 4) != 0) {
                str2 = liveEventLoadData.poster;
            }
            if ((i2 & 8) != 0) {
                str3 = liveEventLoadData.slug;
            }
            if ((i2 & 16) != 0) {
                list = liveEventLoadData.formats;
            }
            if ((i2 & 32) != 0) {
                playZLiveEventInfo = liveEventLoadData.eventInfo;
            }
            List list2 = list;
            PlayZLiveEventInfo playZLiveEventInfo2 = playZLiveEventInfo;
            return liveEventLoadData.copy(i, str, str2, str3, list2, playZLiveEventInfo2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getEventId() {
            return this.eventId;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPoster() {
            return this.poster;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSlug() {
            return this.slug;
        }

        @NotNull
        public final List<PlayZLiveEventFormat> component5() {
            return this.formats;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final PlayZLiveEventInfo getEventInfo() {
            return this.eventInfo;
        }

        @NotNull
        public final LiveEventLoadData copy(int eventId, @NotNull String title, @NotNull String poster, @NotNull String slug, @NotNull List<PlayZLiveEventFormat> formats, @Nullable PlayZLiveEventInfo eventInfo) {
            return new LiveEventLoadData(eventId, title, poster, slug, formats, eventInfo);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LiveEventLoadData)) {
                return false;
            }
            LiveEventLoadData liveEventLoadData = (LiveEventLoadData) other;
            return this.eventId == liveEventLoadData.eventId && Intrinsics.areEqual(this.title, liveEventLoadData.title) && Intrinsics.areEqual(this.poster, liveEventLoadData.poster) && Intrinsics.areEqual(this.slug, liveEventLoadData.slug) && Intrinsics.areEqual(this.formats, liveEventLoadData.formats) && Intrinsics.areEqual(this.eventInfo, liveEventLoadData.eventInfo);
        }

        public int hashCode() {
            return (((((((((this.eventId * 31) + this.title.hashCode()) * 31) + this.poster.hashCode()) * 31) + this.slug.hashCode()) * 31) + this.formats.hashCode()) * 31) + (this.eventInfo == null ? 0 : this.eventInfo.hashCode());
        }

        @NotNull
        public String toString() {
            return "LiveEventLoadData(eventId=" + this.eventId + ", title=" + this.title + ", poster=" + this.poster + ", slug=" + this.slug + ", formats=" + this.formats + ", eventInfo=" + this.eventInfo + ')';
        }

        public LiveEventLoadData(int eventId, @NotNull String title, @NotNull String poster, @NotNull String slug, @NotNull List<PlayZLiveEventFormat> list, @Nullable PlayZLiveEventInfo eventInfo) {
            this.eventId = eventId;
            this.title = title;
            this.poster = poster;
            this.slug = slug;
            this.formats = list;
            this.eventInfo = eventInfo;
        }

        public final int getEventId() {
            return this.eventId;
        }

        @NotNull
        public final String getTitle() {
            return this.title;
        }

        @NotNull
        public final String getPoster() {
            return this.poster;
        }

        @NotNull
        public final String getSlug() {
            return this.slug;
        }

        @NotNull
        public final List<PlayZLiveEventFormat> getFormats() {
            return this.formats;
        }

        @Nullable
        public final PlayZLiveEventInfo getEventInfo() {
            return this.eventInfo;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r45, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r46, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r47) {
        /*
            Method dump skipped, instruction units count: 694
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVLiveEventsProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$1$1$0(String $poster, LiveSearchResponse $this$newLiveSearchResponse) {
        $this$newLiveSearchResponse.setPosterUrl($poster);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r32) {
        /*
            Method dump skipped, instruction units count: 422
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVLiveEventsProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$1$0(String $poster, LiveSearchResponse $this$newLiveSearchResponse) {
        $this$newLiveSearchResponse.setPosterUrl($poster);
        return Unit.INSTANCE;
    }

    @Nullable
    public Object load(@NotNull String url, @NotNull Continuation<? super LoadResponse> continuation) {
        Object obj;
        Object objDecodeFromString;
        AppUtils appUtils = AppUtils.INSTANCE;
        try {
            Result.Companion companion = Result.Companion;
            KType kTypeTypeOf = Reflection.typeOf(LiveEventLoadData.class);
            MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.simple");
            obj = Result.constructor-impl(SerializersKt.serializer(kTypeTypeOf));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.exceptionOrNull-impl(obj) != null) {
            try {
                Result.Companion companion3 = Result.Companion;
                obj = Result.constructor-impl(SerializersModule.getContextual$default(MainAPIKt.getJson().getSerializersModule(), Reflection.getOrCreateKotlinClass(LiveEventLoadData.class), (List) null, 2, (Object) null));
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th2));
            }
        }
        if (Result.isFailure-impl(obj)) {
            obj = null;
        }
        DeserializationStrategy deserializationStrategy = (KSerializer) obj;
        if (deserializationStrategy != null) {
            try {
                objDecodeFromString = MainAPIKt.getJson().decodeFromString(deserializationStrategy, url);
            } catch (SerializationException e) {
                ArchComponentExtKt.logError(e);
                ObjectMapper $this$readValue$iv$iv = MainAPIKt.getMapper();
                objDecodeFromString = $this$readValue$iv$iv.readValue(url, new TypeReference<LiveEventLoadData>() { // from class: com.cncverse.PlayZTVLiveEventsProvider$load$$inlined$parseJson$1
                });
            } catch (Throwable th3) {
                ObjectMapper $this$readValue$iv$iv2 = MainAPIKt.getMapper();
                objDecodeFromString = $this$readValue$iv$iv2.readValue(url, new TypeReference<LiveEventLoadData>() { // from class: com.cncverse.PlayZTVLiveEventsProvider$load$$inlined$parseJson$1
                });
            }
        } else {
            ObjectMapper $this$readValue$iv$iv22 = MainAPIKt.getMapper();
            objDecodeFromString = $this$readValue$iv$iv22.readValue(url, new TypeReference<LiveEventLoadData>() { // from class: com.cncverse.PlayZTVLiveEventsProvider$load$$inlined$parseJson$1
            });
        }
        LiveEventLoadData data = (LiveEventLoadData) objDecodeFromString;
        PlayZLiveEventInfo info = data.getEventInfo();
        StringBuilder $this$load_u24lambda_u240 = new StringBuilder();
        if (info != null) {
            String it = info.getEventType();
            if (it != null) {
                $this$load_u24lambda_u240.append("📌 " + it + '\n');
            }
            String it2 = info.getEventName();
            if (it2 != null) {
                $this$load_u24lambda_u240.append("🏆 " + it2 + '\n');
            }
            String it3 = info.getStartTime();
            if (it3 != null) {
                try {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z", Locale.US);
                    SimpleDateFormat disp = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.US);
                    Date d = df.parse(it3);
                    if (d != null) {
                        $this$load_u24lambda_u240.append("🕐 " + disp.format(d) + '\n');
                    }
                } catch (Exception e2) {
                    $this$load_u24lambda_u240.append("🕐 " + it3 + '\n');
                }
            }
        }
        $this$load_u24lambda_u240.append("\n📡 Available Servers: " + data.getFormats().size());
        String plot = $this$load_u24lambda_u240.toString();
        return MainAPIKt.newLiveStreamLoadResponse(this, data.getTitle(), url, url, new AnonymousClass2(data, plot, null), continuation);
    }

    /* JADX INFO: renamed from: com.cncverse.PlayZTVLiveEventsProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: PlayZTVLiveEventsProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/LiveStreamLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.PlayZTVLiveEventsProvider$load$2", f = "PlayZTVLiveEventsProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<LiveStreamLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ LiveEventLoadData $data;
        final /* synthetic */ String $plot;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LiveEventLoadData liveEventLoadData, String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$data = liveEventLoadData;
            this.$plot = str;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(this.$data, this.$plot, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(LiveStreamLoadResponse liveStreamLoadResponse, Continuation<? super Unit> continuation) {
            return create(liveStreamLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            LiveStreamLoadResponse $this$newLiveStreamLoadResponse = (LiveStreamLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    $this$newLiveStreamLoadResponse.setPosterUrl(this.$data.getPoster());
                    $this$newLiveStreamLoadResponse.setPlot(this.$plot);
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

    /* JADX WARN: Can't wrap try/catch for region: R(11:(1:222)|107|108|214|109|110|200|111|(5:236|113|(2:228|115)(1:119)|120|(4:149|208|150|(1:152)(5:153|210|154|155|194))(15:124|125|230|126|127|234|128|129|238|130|131|232|132|133|(1:135)(5:136|212|137|138|194)))(13:162|220|163|(1:165)|167|168|202|169|170|171|218|172|(1:174)(5:175|240|176|177|194))|193|194) */
    /* JADX WARN: Can't wrap try/catch for region: R(13:162|(1:220)|163|(1:165)|167|168|202|169|170|171|218|172|(1:174)(5:175|240|176|177|194)) */
    /* JADX WARN: Can't wrap try/catch for region: R(15:124|125|230|126|127|234|128|129|238|130|131|232|132|133|(1:135)(5:136|212|137|138|194)) */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x04c7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x04c8, code lost:
    
        r13 = r4;
        r4 = r14;
        r24 = r7;
        r7 = r10;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x04e3, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x04e4, code lost:
    
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r10;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x0506, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x0507, code lost:
    
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r10;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0528, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0529, code lost:
    
        r19 = r11;
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r10;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0712, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0713, code lost:
    
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r9;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x072d, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x072e, code lost:
    
        r29 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0736, code lost:
    
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r9;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0750, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0751, code lost:
    
        r19 = r8;
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r10;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r35;
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0776, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x0777, code lost:
    
        r19 = r8;
        r8 = r13;
        r13 = r4;
        r4 = r8;
        r24 = r7;
        r7 = r10;
        r9 = r24;
        r12 = r2;
        r8 = r12;
        r10 = r0;
        r17 = r22;
        r2 = r34;
        r5 = r5;
        r22 = r15;
     */
    /* JADX WARN: Path cross not found for [B:242:0x02ae, B:81:0x02c3], limit reached: 237 */
    /* JADX WARN: Path cross not found for [B:88:0x0313, B:92:0x031c], limit reached: 237 */
    /* JADX WARN: Removed duplicated region for block: B:198:0x07f6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0304 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0346  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:138:0x04ac -> B:194:0x07d4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:155:0x05e5 -> B:194:0x07d4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:175:0x06d7 -> B:240:0x06ed). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:193:0x07c3 -> B:194:0x07d4). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r34, boolean r35, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r36, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r37, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r38) {
        /*
            Method dump skipped, instruction units count: 2060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.PlayZTVLiveEventsProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    @Nullable
    public Interceptor getVideoInterceptor(@NotNull ExtractorLink extractorLink) {
        return new Interceptor() { // from class: com.cncverse.PlayZTVLiveEventsProvider.getVideoInterceptor.1
            public Response intercept(Interceptor.Chain chain) {
                Request request = chain.request();
                String fixedUrl = new Regex("(?i)%2f").replace(request.url().toString(), "/");
                return chain.proceed(request.newBuilder().url(fixedUrl).header("User-Agent", "Mozilla/5.0 (Linux; Android 10; Pixel 3 XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Mobile Safari/537.36").build());
            }
        };
    }

    private final Pair<String, Map<String, String>> parseStreamLink(String link) {
        String string;
        List parts;
        Iterable $this$mapNotNull$iv;
        int $i$f$mapNotNull;
        Pair pair;
        List parts2 = StringsKt.split$default(link, new String[]{"|"}, false, 0, 6, (Object) null);
        String str = (String) CollectionsKt.firstOrNull(parts2);
        if (str == null || (string = StringsKt.trim(str).toString()) == null) {
            string = "";
        }
        String url = string;
        String url2 = StringsKt.replace$default(url, "%2F", "/", false, 4, (Object) null);
        Iterable $this$mapNotNull$iv2 = CollectionsKt.drop(parts2, 1);
        int $i$f$mapNotNull2 = 0;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv2) {
            String part = (String) element$iv$iv$iv;
            int eq = StringsKt.indexOf$default(part, '=', 0, false, 6, (Object) null);
            if (eq > 0) {
                parts = parts2;
                String strSubstring = part.substring(0, eq);
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                String string2 = StringsKt.trim(strSubstring).toString();
                $i$f$mapNotNull = $i$f$mapNotNull2;
                int $i$f$mapNotNull3 = eq + 1;
                String strSubstring2 = part.substring($i$f$mapNotNull3);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                pair = TuplesKt.to(string2, StringsKt.trim(strSubstring2).toString());
            } else {
                parts = parts2;
                $this$mapNotNull$iv = $this$mapNotNull$iv2;
                $i$f$mapNotNull = $i$f$mapNotNull2;
                pair = null;
            }
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
            parts2 = parts;
            $this$mapNotNull$iv2 = $this$mapNotNull$iv;
            $i$f$mapNotNull2 = $i$f$mapNotNull;
        }
        Map headers = MapsKt.toMap((List) destination$iv$iv);
        return TuplesKt.to(url2, headers);
    }

    private final String hexToBase64(String hex) {
        Iterable $this$map$iv = StringsKt.chunked(StringsKt.replace$default(hex, "-", "", false, 4, (Object) null), 2);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(Byte.valueOf((byte) Integer.parseInt(it, CharsKt.checkRadix(16))));
        }
        byte[] bytes = CollectionsKt.toByteArray((List) destination$iv$iv);
        return Base64.encodeToString(bytes, 11);
    }
}
