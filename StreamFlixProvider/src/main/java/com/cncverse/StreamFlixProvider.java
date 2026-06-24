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
import com.cncverse.StreamFlixWebSocketExtractor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lagradost.cloudstream3.MainAPI;
import com.lagradost.cloudstream3.MovieLoadResponse;
import com.lagradost.cloudstream3.MovieSearchResponse;
import com.lagradost.cloudstream3.Score;
import com.lagradost.cloudstream3.SearchQuality;
import com.lagradost.cloudstream3.TvSeriesLoadResponse;
import com.lagradost.cloudstream3.TvSeriesSearchResponse;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: StreamFlixProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nStreamFlixProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamFlixProvider.kt\ncom/cncverse/StreamFlixProvider\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,777:1\n63#2:778\n64#2,15:780\n63#2:797\n64#2,15:799\n63#2:830\n64#2,15:832\n63#2:854\n64#2,15:856\n1#3:779\n1#3:798\n1#3:831\n1#3:855\n1#3:873\n50#4:795\n43#4:796\n50#4:814\n43#4:815\n50#4:847\n43#4:848\n50#4:871\n43#4:872\n777#5:816\n873#5,2:817\n1586#5:819\n1661#5,3:820\n777#5:823\n873#5,2:824\n1586#5:826\n1661#5,3:827\n777#5:849\n873#5,2:850\n1915#5,2:852\n1915#5,2:878\n1915#5,2:880\n1915#5,2:882\n1915#5,2:884\n1915#5,2:886\n221#6:874\n221#6,2:875\n222#6:877\n*S KotlinDebug\n*F\n+ 1 StreamFlixProvider.kt\ncom/cncverse/StreamFlixProvider\n*L\n115#1:778\n115#1:780,15\n154#1:797\n154#1:799,15\n221#1:830\n221#1:832,15\n292#1:854\n292#1:856,15\n115#1:779\n154#1:798\n221#1:831\n292#1:855\n115#1:795\n115#1:796\n154#1:814\n154#1:815\n221#1:847\n221#1:848\n292#1:871\n292#1:872\n156#1:816\n156#1:817,2\n156#1:819\n156#1:820,3\n168#1:823\n168#1:824,2\n168#1:826\n168#1:827,3\n223#1:849\n223#1:850,2\n231#1:852,2\n426#1:878,2\n442#1:880,2\n473#1:882,2\n494#1:884,2\n510#1:886,2\n351#1:874\n352#1:875,2\n351#1:877\n*E\n"})
public final class StreamFlixProvider extends MainAPI {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull

    @Nullable
    private static Context context;

    @Nullable
    private ConfigResponse configData;

    @NotNull
    private String mainUrl = "https://api.streamflix.app";

    @NotNull
    private String name = "StreamFlix 2.0";

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(new TvType[]{TvType.Movie, TvType.TvSeries});

    @NotNull
    private String lang = "ta";
    private final boolean hasMainPage = true;
    private final boolean hasQuickSearch = true;

    @NotNull
    private final StreamFlixWebSocketExtractor webSocketExtractor = new StreamFlixWebSocketExtractor();

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$getConfig$1, reason: invalid class name */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider", f = "StreamFlixProvider.kt", i = {0}, l = {114}, m = "getConfig", n = {"headers"}, nl = {115}, s = {"L$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return StreamFlixProvider.this.getConfig((Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$getEpisodesFromWebSocket$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider", f = "StreamFlixProvider.kt", i = {0, 0, 0}, l = {349}, m = "getEpisodesFromWebSocket", n = {"movieKey", "episodes", "totalSeasons"}, nl = {351}, s = {"L$0", "L$1", "I$0"}, v = 2)
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
            return StreamFlixProvider.this.getEpisodesFromWebSocket(null, 0, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$getMainPage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider", f = "StreamFlixProvider.kt", i = {0, 0, 0, 0}, l = {152}, m = "getMainPage", n = {"request", "items", "headers", "page"}, nl = {154}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 2)
    static final class C00011 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00011(Continuation<? super C00011> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StreamFlixProvider.this.getMainPage(0, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$load$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider", f = "StreamFlixProvider.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4}, l = {272, 291, 306, 308, 322}, m = "load", n = {"url", "str", "movieKey", "type", "url", "str", "movieKey", "type", "headers", "url", "str", "movieKey", "type", "headers", "response", "data", "item", "movieName", "seasonCount", "url", "str", "movieKey", "type", "headers", "response", "data", "item", "movieName", "episodes", "seasonCount", "url", "str", "movieKey", "type", "headers", "response", "data", "item", "movieName"}, nl = {283, 292, 308, 322, 298}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8"}, v = 2)
    static final class C00021 extends ContinuationImpl {
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
            return StreamFlixProvider.this.load(null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$loadLinks$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider", f = "StreamFlixProvider.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, l = {420, 429, 445, 476, 497, 513}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "str", "isCasting", "data", "subtitleCallback", "callback", "str", "config", "$this$forEach$iv", "element$iv", "baseUrl", "videoUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-StreamFlixProvider$loadLinks$3", "data", "subtitleCallback", "callback", "str", "config", "$this$forEach$iv", "element$iv", "baseUrl", "videoUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-StreamFlixProvider$loadLinks$4", "data", "subtitleCallback", "callback", "str", "config", "parts", "movieKey", "episodeInfo", "seasonMatch", "episodeMatch", "season", "episode", "$this$forEach$iv", "element$iv", "baseUrl", "videoUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-StreamFlixProvider$loadLinks$5", "data", "subtitleCallback", "callback", "str", "config", "movieLink", "$this$forEach$iv", "element$iv", "baseUrl", "videoUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-StreamFlixProvider$loadLinks$6", "data", "subtitleCallback", "callback", "str", "config", "movieLink", "$this$forEach$iv", "element$iv", "baseUrl", "videoUrl", "isCasting", "$i$f$forEach", "$i$a$-forEach-StreamFlixProvider$loadLinks$7"}, nl = {422, 428, 444, 475, 496, 512}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$7", "L$8", "L$9", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$14", "L$15", "L$16", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$8", "L$9", "L$10", "Z$0", "I$0", "I$1"}, v = 2)
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
            return StreamFlixProvider.this.loadLinks(null, false, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider", f = "StreamFlixProvider.kt", i = {0, 0, 0}, l = {220}, m = "search", n = {"query", "searchResults", "headers"}, nl = {221}, s = {"L$0", "L$1", "L$2"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return StreamFlixProvider.this.search(null, (Continuation) this);
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/cncverse/StreamFlixProvider$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "OMG10", "", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return StreamFlixProvider.context;
        }

        public final void setContext(@Nullable Context context) {
            StreamFlixProvider.context = context;
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

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/cncverse/StreamFlixProvider$StreamFlixData;", "", "data", "", "Lcom/cncverse/StreamFlixProvider$StreamFlixItem;", "<init>", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StreamFlixData {

        @JsonProperty("data")
        @NotNull
        private final List<StreamFlixItem> data;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StreamFlixData copy$default(StreamFlixData streamFlixData, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = streamFlixData.data;
            }
            return streamFlixData.copy(list);
        }

        @NotNull
        public final List<StreamFlixItem> component1() {
            return this.data;
        }

        @NotNull
        public final StreamFlixData copy(@JsonProperty("data") @NotNull List<StreamFlixItem> data) {
            return new StreamFlixData(data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StreamFlixData) && Intrinsics.areEqual(this.data, ((StreamFlixData) other).data);
        }

        public int hashCode() {
            return this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "StreamFlixData(data=" + this.data + ')';
        }

        public StreamFlixData(@JsonProperty("data") @NotNull List<StreamFlixItem> list) {
            this.data = list;
        }

        @NotNull
        public final List<StreamFlixItem> getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b0\b\u0086\b\u0018\u00002\u00020\u0001BÏ\u0001\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0001\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b\u0012\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u0015\u0012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00104\u001a\u00020\u000bHÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010=\u001a\u0004\u0018\u00010\u0015HÆ\u0003¢\u0006\u0002\u0010+J\u000b\u0010>\u001a\u0004\u0018\u00010\u0005HÆ\u0003JÖ\u0001\u0010?\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\n\u001a\u00020\u000b2\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010@J\u0014\u0010A\u001a\u00020\u00032\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010C\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010D\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0019R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001bR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001bR\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001bR\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001bR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001bR\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00158\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010,\u001a\u0004\b*\u0010+R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001b¨\u0006E"}, d2 = {"Lcom/cncverse/StreamFlixProvider$StreamFlixItem;", "", "isTV", "", "movieName", "", "movieDesc", "moviePoster", "movieBanner", "movieYear", "movieRating", "", "movieType", "movieInfo", "movieDuration", "movieKey", "movieLink", "movieTrailer", "movieImdb", "tmdb", "movieViews", "", "newSeason", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "()Z", "getMovieName", "()Ljava/lang/String;", "getMovieDesc", "getMoviePoster", "getMovieBanner", "getMovieYear", "getMovieRating", "()D", "getMovieType", "getMovieInfo", "getMovieDuration", "getMovieKey", "getMovieLink", "getMovieTrailer", "getMovieImdb", "getTmdb", "getMovieViews", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNewSeason", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/cncverse/StreamFlixProvider$StreamFlixItem;", "equals", "other", "hashCode", "toString", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class StreamFlixItem {

        @JsonProperty("isTV")
        private final boolean isTV;

        @JsonProperty("moviebanner")
        @Nullable
        private final String movieBanner;

        @JsonProperty("moviedesc")
        @Nullable
        private final String movieDesc;

        @JsonProperty("movieduration")
        @Nullable
        private final String movieDuration;

        @JsonProperty("movieimdb")
        @Nullable
        private final String movieImdb;

        @JsonProperty("movieinfo")
        @Nullable
        private final String movieInfo;

        @JsonProperty("moviekey")
        @Nullable
        private final String movieKey;

        @JsonProperty("movielink")
        @Nullable
        private final String movieLink;

        @JsonProperty("moviename")
        @Nullable
        private final String movieName;

        @JsonProperty("movieposter")
        @Nullable
        private final String moviePoster;

        @JsonProperty("movierating")
        private final double movieRating;

        @JsonProperty("movietrailer")
        @Nullable
        private final String movieTrailer;

        @JsonProperty("movietype")
        @Nullable
        private final String movieType;

        @JsonProperty("movieviews")
        @Nullable
        private final Integer movieViews;

        @JsonProperty("movieyear")
        @Nullable
        private final String movieYear;

        @JsonProperty("newseason")
        @Nullable
        private final String newSeason;

        @JsonProperty("tmdb")
        @Nullable
        private final String tmdb;

        public static /* synthetic */ StreamFlixItem copy$default(StreamFlixItem streamFlixItem, boolean z, String str, String str2, String str3, String str4, String str5, double d, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, Integer num, String str14, int i, Object obj) {
            String str15;
            Integer num2;
            boolean z2 = (i & 1) != 0 ? streamFlixItem.isTV : z;
            String str16 = (i & 2) != 0 ? streamFlixItem.movieName : str;
            String str17 = (i & 4) != 0 ? streamFlixItem.movieDesc : str2;
            String str18 = (i & 8) != 0 ? streamFlixItem.moviePoster : str3;
            String str19 = (i & 16) != 0 ? streamFlixItem.movieBanner : str4;
            String str20 = (i & 32) != 0 ? streamFlixItem.movieYear : str5;
            double d2 = (i & 64) != 0 ? streamFlixItem.movieRating : d;
            String str21 = (i & 128) != 0 ? streamFlixItem.movieType : str6;
            String str22 = (i & 256) != 0 ? streamFlixItem.movieInfo : str7;
            String str23 = (i & 512) != 0 ? streamFlixItem.movieDuration : str8;
            String str24 = (i & 1024) != 0 ? streamFlixItem.movieKey : str9;
            String str25 = (i & 2048) != 0 ? streamFlixItem.movieLink : str10;
            String str26 = (i & 4096) != 0 ? streamFlixItem.movieTrailer : str11;
            boolean z3 = z2;
            String str27 = (i & 8192) != 0 ? streamFlixItem.movieImdb : str12;
            String str28 = (i & 16384) != 0 ? streamFlixItem.tmdb : str13;
            Integer num3 = (i & 32768) != 0 ? streamFlixItem.movieViews : num;
            if ((i & 65536) != 0) {
                num2 = num3;
                str15 = streamFlixItem.newSeason;
            } else {
                str15 = str14;
                num2 = num3;
            }
            return streamFlixItem.copy(z3, str16, str17, str18, str19, str20, d2, str21, str22, str23, str24, str25, str26, str27, str28, num2, str15);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsTV() {
            return this.isTV;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getMovieDuration() {
            return this.movieDuration;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getMovieKey() {
            return this.movieKey;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getMovieLink() {
            return this.movieLink;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getMovieTrailer() {
            return this.movieTrailer;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getMovieImdb() {
            return this.movieImdb;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getTmdb() {
            return this.tmdb;
        }

        @Nullable
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Integer getMovieViews() {
            return this.movieViews;
        }

        @Nullable
        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getNewSeason() {
            return this.newSeason;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMovieName() {
            return this.movieName;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMovieDesc() {
            return this.movieDesc;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getMoviePoster() {
            return this.moviePoster;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getMovieBanner() {
            return this.movieBanner;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getMovieYear() {
            return this.movieYear;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final double getMovieRating() {
            return this.movieRating;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getMovieType() {
            return this.movieType;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getMovieInfo() {
            return this.movieInfo;
        }

        @NotNull
        public final StreamFlixItem copy(@JsonProperty("isTV") boolean isTV, @JsonProperty("moviename") @Nullable String movieName, @JsonProperty("moviedesc") @Nullable String movieDesc, @JsonProperty("movieposter") @Nullable String moviePoster, @JsonProperty("moviebanner") @Nullable String movieBanner, @JsonProperty("movieyear") @Nullable String movieYear, @JsonProperty("movierating") double movieRating, @JsonProperty("movietype") @Nullable String movieType, @JsonProperty("movieinfo") @Nullable String movieInfo, @JsonProperty("movieduration") @Nullable String movieDuration, @JsonProperty("moviekey") @Nullable String movieKey, @JsonProperty("movielink") @Nullable String movieLink, @JsonProperty("movietrailer") @Nullable String movieTrailer, @JsonProperty("movieimdb") @Nullable String movieImdb, @JsonProperty("tmdb") @Nullable String tmdb, @JsonProperty("movieviews") @Nullable Integer movieViews, @JsonProperty("newseason") @Nullable String newSeason) {
            return new StreamFlixItem(isTV, movieName, movieDesc, moviePoster, movieBanner, movieYear, movieRating, movieType, movieInfo, movieDuration, movieKey, movieLink, movieTrailer, movieImdb, tmdb, movieViews, newSeason);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof StreamFlixItem)) {
                return false;
            }
            StreamFlixItem streamFlixItem = (StreamFlixItem) other;
            return this.isTV == streamFlixItem.isTV && Intrinsics.areEqual(this.movieName, streamFlixItem.movieName) && Intrinsics.areEqual(this.movieDesc, streamFlixItem.movieDesc) && Intrinsics.areEqual(this.moviePoster, streamFlixItem.moviePoster) && Intrinsics.areEqual(this.movieBanner, streamFlixItem.movieBanner) && Intrinsics.areEqual(this.movieYear, streamFlixItem.movieYear) && Double.compare(this.movieRating, streamFlixItem.movieRating) == 0 && Intrinsics.areEqual(this.movieType, streamFlixItem.movieType) && Intrinsics.areEqual(this.movieInfo, streamFlixItem.movieInfo) && Intrinsics.areEqual(this.movieDuration, streamFlixItem.movieDuration) && Intrinsics.areEqual(this.movieKey, streamFlixItem.movieKey) && Intrinsics.areEqual(this.movieLink, streamFlixItem.movieLink) && Intrinsics.areEqual(this.movieTrailer, streamFlixItem.movieTrailer) && Intrinsics.areEqual(this.movieImdb, streamFlixItem.movieImdb) && Intrinsics.areEqual(this.tmdb, streamFlixItem.tmdb) && Intrinsics.areEqual(this.movieViews, streamFlixItem.movieViews) && Intrinsics.areEqual(this.newSeason, streamFlixItem.newSeason);
        }

        public int hashCode() {
            return (((((((((((((((((((((((((((((((StreamFlixProvider$StreamFlixItem$$ExternalSyntheticBackport0.m(this.isTV) * 31) + (this.movieName == null ? 0 : this.movieName.hashCode())) * 31) + (this.movieDesc == null ? 0 : this.movieDesc.hashCode())) * 31) + (this.moviePoster == null ? 0 : this.moviePoster.hashCode())) * 31) + (this.movieBanner == null ? 0 : this.movieBanner.hashCode())) * 31) + (this.movieYear == null ? 0 : this.movieYear.hashCode())) * 31) + StreamFlixProvider$StreamFlixItem$$ExternalSyntheticBackport1.m(this.movieRating)) * 31) + (this.movieType == null ? 0 : this.movieType.hashCode())) * 31) + (this.movieInfo == null ? 0 : this.movieInfo.hashCode())) * 31) + (this.movieDuration == null ? 0 : this.movieDuration.hashCode())) * 31) + (this.movieKey == null ? 0 : this.movieKey.hashCode())) * 31) + (this.movieLink == null ? 0 : this.movieLink.hashCode())) * 31) + (this.movieTrailer == null ? 0 : this.movieTrailer.hashCode())) * 31) + (this.movieImdb == null ? 0 : this.movieImdb.hashCode())) * 31) + (this.tmdb == null ? 0 : this.tmdb.hashCode())) * 31) + (this.movieViews == null ? 0 : this.movieViews.hashCode())) * 31) + (this.newSeason != null ? this.newSeason.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("StreamFlixItem(isTV=").append(this.isTV).append(", movieName=").append(this.movieName).append(", movieDesc=").append(this.movieDesc).append(", moviePoster=").append(this.moviePoster).append(", movieBanner=").append(this.movieBanner).append(", movieYear=").append(this.movieYear).append(", movieRating=").append(this.movieRating).append(", movieType=").append(this.movieType).append(", movieInfo=").append(this.movieInfo).append(", movieDuration=").append(this.movieDuration).append(", movieKey=").append(this.movieKey).append(", movieLink=");
            sb.append(this.movieLink).append(", movieTrailer=").append(this.movieTrailer).append(", movieImdb=").append(this.movieImdb).append(", tmdb=").append(this.tmdb).append(", movieViews=").append(this.movieViews).append(", newSeason=").append(this.newSeason).append(')');
            return sb.toString();
        }

        public StreamFlixItem(@JsonProperty("isTV") boolean isTV, @JsonProperty("moviename") @Nullable String movieName, @JsonProperty("moviedesc") @Nullable String movieDesc, @JsonProperty("movieposter") @Nullable String moviePoster, @JsonProperty("moviebanner") @Nullable String movieBanner, @JsonProperty("movieyear") @Nullable String movieYear, @JsonProperty("movierating") double movieRating, @JsonProperty("movietype") @Nullable String movieType, @JsonProperty("movieinfo") @Nullable String movieInfo, @JsonProperty("movieduration") @Nullable String movieDuration, @JsonProperty("moviekey") @Nullable String movieKey, @JsonProperty("movielink") @Nullable String movieLink, @JsonProperty("movietrailer") @Nullable String movieTrailer, @JsonProperty("movieimdb") @Nullable String movieImdb, @JsonProperty("tmdb") @Nullable String tmdb, @JsonProperty("movieviews") @Nullable Integer movieViews, @JsonProperty("newseason") @Nullable String newSeason) {
            this.isTV = isTV;
            this.movieName = movieName;
            this.movieDesc = movieDesc;
            this.moviePoster = moviePoster;
            this.movieBanner = movieBanner;
            this.movieYear = movieYear;
            this.movieRating = movieRating;
            this.movieType = movieType;
            this.movieInfo = movieInfo;
            this.movieDuration = movieDuration;
            this.movieKey = movieKey;
            this.movieLink = movieLink;
            this.movieTrailer = movieTrailer;
            this.movieImdb = movieImdb;
            this.tmdb = tmdb;
            this.movieViews = movieViews;
            this.newSeason = newSeason;
        }

        public final boolean isTV() {
            return this.isTV;
        }

        @Nullable
        public final String getMovieName() {
            return this.movieName;
        }

        @Nullable
        public final String getMovieDesc() {
            return this.movieDesc;
        }

        @Nullable
        public final String getMoviePoster() {
            return this.moviePoster;
        }

        @Nullable
        public final String getMovieBanner() {
            return this.movieBanner;
        }

        @Nullable
        public final String getMovieYear() {
            return this.movieYear;
        }

        public final double getMovieRating() {
            return this.movieRating;
        }

        @Nullable
        public final String getMovieType() {
            return this.movieType;
        }

        @Nullable
        public final String getMovieInfo() {
            return this.movieInfo;
        }

        @Nullable
        public final String getMovieDuration() {
            return this.movieDuration;
        }

        @Nullable
        public final String getMovieKey() {
            return this.movieKey;
        }

        @Nullable
        public final String getMovieLink() {
            return this.movieLink;
        }

        @Nullable
        public final String getMovieTrailer() {
            return this.movieTrailer;
        }

        @Nullable
        public final String getMovieImdb() {
            return this.movieImdb;
        }

        @Nullable
        public final String getTmdb() {
            return this.tmdb;
        }

        @Nullable
        public final Integer getMovieViews() {
            return this.movieViews;
        }

        @Nullable
        public final String getNewSeason() {
            return this.newSeason;
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b%\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u000e\b\u0001\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u000b\u001a\u00020\u0004\u0012\b\b\u0001\u0010\f\u001a\u00020\r\u0012\b\b\u0001\u0010\u000e\u001a\u00020\r\u0012\b\b\u0001\u0010\u000f\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0010\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\t\u0010(\u001a\u00020\u0004HÆ\u0003J\t\u0010)\u001a\u00020\rHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\u0004HÆ\u0003J\t\u0010,\u001a\u00020\u0004HÆ\u0003J\u008f\u0001\u0010-\u001a\u00020\u00002\u000e\b\u0003\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0003\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0003\u0010\b\u001a\u00020\t2\b\b\u0003\u0010\n\u001a\u00020\u00042\b\b\u0003\u0010\u000b\u001a\u00020\u00042\b\b\u0003\u0010\f\u001a\u00020\r2\b\b\u0003\u0010\u000e\u001a\u00020\r2\b\b\u0003\u0010\u000f\u001a\u00020\u00042\b\b\u0003\u0010\u0010\u001a\u00020\u0004HÆ\u0001J\u0014\u0010.\u001a\u00020\r2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00100\u001a\u00020\tHÖ\u0081\u0004J\n\u00101\u001a\u00020\u0004HÖ\u0081\u0004R\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\n\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\u000b\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u000e\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0016\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0016\u0010\u0010\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001b¨\u00062"}, d2 = {"Lcom/cncverse/StreamFlixProvider$ConfigResponse;", "", "movies", "", "", "tv", "premium", "download", "latest", "", "banner", "video", "newApp", "", "notice", "title", "text", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;)V", "getMovies", "()Ljava/util/List;", "getTv", "getPremium", "getDownload", "getLatest", "()I", "getBanner", "()Ljava/lang/String;", "getVideo", "getNewApp", "()Z", "getNotice", "getTitle", "getText", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "toString", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ConfigResponse {

        @JsonProperty("banner")
        @NotNull
        private final String banner;

        @JsonProperty("download")
        @NotNull
        private final List<String> download;

        @JsonProperty("latest")
        private final int latest;

        @JsonProperty("movies")
        @NotNull
        private final List<String> movies;

        @JsonProperty("newapp")
        private final boolean newApp;

        @JsonProperty("notice")
        private final boolean notice;

        @JsonProperty("premium")
        @NotNull
        private final List<String> premium;

        @JsonProperty("text")
        @NotNull
        private final String text;

        @JsonProperty("title")
        @NotNull
        private final String title;

        @JsonProperty("tv")
        @NotNull
        private final List<String> tv;

        @JsonProperty("video")
        @NotNull
        private final String video;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ConfigResponse copy$default(ConfigResponse configResponse, List list, List list2, List list3, List list4, int i, String str, String str2, boolean z, boolean z2, String str3, String str4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                list = configResponse.movies;
            }
            if ((i2 & 2) != 0) {
                list2 = configResponse.tv;
            }
            if ((i2 & 4) != 0) {
                list3 = configResponse.premium;
            }
            if ((i2 & 8) != 0) {
                list4 = configResponse.download;
            }
            if ((i2 & 16) != 0) {
                i = configResponse.latest;
            }
            if ((i2 & 32) != 0) {
                str = configResponse.banner;
            }
            if ((i2 & 64) != 0) {
                str2 = configResponse.video;
            }
            if ((i2 & 128) != 0) {
                z = configResponse.newApp;
            }
            if ((i2 & 256) != 0) {
                z2 = configResponse.notice;
            }
            if ((i2 & 512) != 0) {
                str3 = configResponse.title;
            }
            if ((i2 & 1024) != 0) {
                str4 = configResponse.text;
            }
            String str5 = str3;
            String str6 = str4;
            boolean z3 = z;
            boolean z4 = z2;
            String str7 = str;
            String str8 = str2;
            int i3 = i;
            List list5 = list3;
            return configResponse.copy(list, list2, list5, list4, i3, str7, str8, z3, z4, str5, str6);
        }

        @NotNull
        public final List<String> component1() {
            return this.movies;
        }

        @NotNull
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @NotNull
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getText() {
            return this.text;
        }

        @NotNull
        public final List<String> component2() {
            return this.tv;
        }

        @NotNull
        public final List<String> component3() {
            return this.premium;
        }

        @NotNull
        public final List<String> component4() {
            return this.download;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getLatest() {
            return this.latest;
        }

        @NotNull
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getBanner() {
            return this.banner;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getVideo() {
            return this.video;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getNewApp() {
            return this.newApp;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getNotice() {
            return this.notice;
        }

        @NotNull
        public final ConfigResponse copy(@JsonProperty("movies") @NotNull List<String> movies, @JsonProperty("tv") @NotNull List<String> tv, @JsonProperty("premium") @NotNull List<String> premium, @JsonProperty("download") @NotNull List<String> download, @JsonProperty("latest") int latest, @JsonProperty("banner") @NotNull String banner, @JsonProperty("video") @NotNull String video, @JsonProperty("newapp") boolean newApp, @JsonProperty("notice") boolean notice, @JsonProperty("title") @NotNull String title, @JsonProperty("text") @NotNull String text) {
            return new ConfigResponse(movies, tv, premium, download, latest, banner, video, newApp, notice, title, text);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ConfigResponse)) {
                return false;
            }
            ConfigResponse configResponse = (ConfigResponse) other;
            return Intrinsics.areEqual(this.movies, configResponse.movies) && Intrinsics.areEqual(this.tv, configResponse.tv) && Intrinsics.areEqual(this.premium, configResponse.premium) && Intrinsics.areEqual(this.download, configResponse.download) && this.latest == configResponse.latest && Intrinsics.areEqual(this.banner, configResponse.banner) && Intrinsics.areEqual(this.video, configResponse.video) && this.newApp == configResponse.newApp && this.notice == configResponse.notice && Intrinsics.areEqual(this.title, configResponse.title) && Intrinsics.areEqual(this.text, configResponse.text);
        }

        public int hashCode() {
            return (((((((((((((((((((this.movies.hashCode() * 31) + this.tv.hashCode()) * 31) + this.premium.hashCode()) * 31) + this.download.hashCode()) * 31) + this.latest) * 31) + this.banner.hashCode()) * 31) + this.video.hashCode()) * 31) + StreamFlixProvider$ConfigResponse$$ExternalSyntheticBackport0.m(this.newApp)) * 31) + StreamFlixProvider$ConfigResponse$$ExternalSyntheticBackport0.m(this.notice)) * 31) + this.title.hashCode()) * 31) + this.text.hashCode();
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ConfigResponse(movies=").append(this.movies).append(", tv=").append(this.tv).append(", premium=").append(this.premium).append(", download=").append(this.download).append(", latest=").append(this.latest).append(", banner=").append(this.banner).append(", video=").append(this.video).append(", newApp=").append(this.newApp).append(", notice=").append(this.notice).append(", title=").append(this.title).append(", text=").append(this.text).append(')');
            return sb.toString();
        }

        public ConfigResponse(@JsonProperty("movies") @NotNull List<String> list, @JsonProperty("tv") @NotNull List<String> list2, @JsonProperty("premium") @NotNull List<String> list3, @JsonProperty("download") @NotNull List<String> list4, @JsonProperty("latest") int latest, @JsonProperty("banner") @NotNull String banner, @JsonProperty("video") @NotNull String video, @JsonProperty("newapp") boolean newApp, @JsonProperty("notice") boolean notice, @JsonProperty("title") @NotNull String title, @JsonProperty("text") @NotNull String text) {
            this.movies = list;
            this.tv = list2;
            this.premium = list3;
            this.download = list4;
            this.latest = latest;
            this.banner = banner;
            this.video = video;
            this.newApp = newApp;
            this.notice = notice;
            this.title = title;
            this.text = text;
        }

        @NotNull
        public final List<String> getMovies() {
            return this.movies;
        }

        @NotNull
        public final List<String> getTv() {
            return this.tv;
        }

        @NotNull
        public final List<String> getPremium() {
            return this.premium;
        }

        @NotNull
        public final List<String> getDownload() {
            return this.download;
        }

        public final int getLatest() {
            return this.latest;
        }

        @NotNull
        public final String getBanner() {
            return this.banner;
        }

        @NotNull
        public final String getVideo() {
            return this.video;
        }

        public final boolean getNewApp() {
            return this.newApp;
        }

        public final boolean getNotice() {
            return this.notice;
        }

        @NotNull
        public final String getTitle() {
            return this.title;
        }

        @NotNull
        public final String getText() {
            return this.text;
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/cncverse/StreamFlixProvider$WebSocketRequest;", "", "type", "", "data", "Lcom/cncverse/StreamFlixProvider$WebSocketData;", "<init>", "(Ljava/lang/String;Lcom/cncverse/StreamFlixProvider$WebSocketData;)V", "getType", "()Ljava/lang/String;", "getData", "()Lcom/cncverse/StreamFlixProvider$WebSocketData;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WebSocketRequest {

        @JsonProperty("d")
        @NotNull
        private final WebSocketData data;

        @JsonProperty("t")
        @NotNull
        private final String type;

        public static /* synthetic */ WebSocketRequest copy$default(WebSocketRequest webSocketRequest, String str, WebSocketData webSocketData, int i, Object obj) {
            if ((i & 1) != 0) {
                str = webSocketRequest.type;
            }
            if ((i & 2) != 0) {
                webSocketData = webSocketRequest.data;
            }
            return webSocketRequest.copy(str, webSocketData);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final WebSocketData getData() {
            return this.data;
        }

        @NotNull
        public final WebSocketRequest copy(@JsonProperty("t") @NotNull String type, @JsonProperty("d") @NotNull WebSocketData data) {
            return new WebSocketRequest(type, data);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WebSocketRequest)) {
                return false;
            }
            WebSocketRequest webSocketRequest = (WebSocketRequest) other;
            return Intrinsics.areEqual(this.type, webSocketRequest.type) && Intrinsics.areEqual(this.data, webSocketRequest.data);
        }

        public int hashCode() {
            return (this.type.hashCode() * 31) + this.data.hashCode();
        }

        @NotNull
        public String toString() {
            return "WebSocketRequest(type=" + this.type + ", data=" + this.data + ')';
        }

        public WebSocketRequest(@JsonProperty("t") @NotNull String type, @JsonProperty("d") @NotNull WebSocketData data) {
            this.type = type;
            this.data = data;
        }

        @NotNull
        public final String getType() {
            return this.type;
        }

        @NotNull
        public final WebSocketData getData() {
            return this.data;
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/cncverse/StreamFlixProvider$WebSocketData;", "", "action", "", "request", "", "body", "Lcom/cncverse/StreamFlixProvider$WebSocketBody;", "<init>", "(Ljava/lang/String;ILcom/cncverse/StreamFlixProvider$WebSocketBody;)V", "getAction", "()Ljava/lang/String;", "getRequest", "()I", "getBody", "()Lcom/cncverse/StreamFlixProvider$WebSocketBody;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WebSocketData {

        @JsonProperty("a")
        @NotNull
        private final String action;

        @JsonProperty("b")
        @NotNull
        private final WebSocketBody body;

        @JsonProperty("r")
        private final int request;

        public static /* synthetic */ WebSocketData copy$default(WebSocketData webSocketData, String str, int i, WebSocketBody webSocketBody, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = webSocketData.action;
            }
            if ((i2 & 2) != 0) {
                i = webSocketData.request;
            }
            if ((i2 & 4) != 0) {
                webSocketBody = webSocketData.body;
            }
            return webSocketData.copy(str, i, webSocketBody);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAction() {
            return this.action;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRequest() {
            return this.request;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final WebSocketBody getBody() {
            return this.body;
        }

        @NotNull
        public final WebSocketData copy(@JsonProperty("a") @NotNull String action, @JsonProperty("r") int request, @JsonProperty("b") @NotNull WebSocketBody body) {
            return new WebSocketData(action, request, body);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WebSocketData)) {
                return false;
            }
            WebSocketData webSocketData = (WebSocketData) other;
            return Intrinsics.areEqual(this.action, webSocketData.action) && this.request == webSocketData.request && Intrinsics.areEqual(this.body, webSocketData.body);
        }

        public int hashCode() {
            return (((this.action.hashCode() * 31) + this.request) * 31) + this.body.hashCode();
        }

        @NotNull
        public String toString() {
            return "WebSocketData(action=" + this.action + ", request=" + this.request + ", body=" + this.body + ')';
        }

        public WebSocketData(@JsonProperty("a") @NotNull String action, @JsonProperty("r") int request, @JsonProperty("b") @NotNull WebSocketBody body) {
            this.action = action;
            this.request = request;
            this.body = body;
        }

        @NotNull
        public final String getAction() {
            return this.action;
        }

        public final int getRequest() {
            return this.request;
        }

        @NotNull
        public final WebSocketBody getBody() {
            return this.body;
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/cncverse/StreamFlixProvider$WebSocketBody;", "", "path", "", "hash", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "getHash", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class WebSocketBody {

        @JsonProperty("h")
        @NotNull
        private final String hash;

        @JsonProperty("p")
        @NotNull
        private final String path;

        public static /* synthetic */ WebSocketBody copy$default(WebSocketBody webSocketBody, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = webSocketBody.path;
            }
            if ((i & 2) != 0) {
                str2 = webSocketBody.hash;
            }
            return webSocketBody.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getHash() {
            return this.hash;
        }

        @NotNull
        public final WebSocketBody copy(@JsonProperty("p") @NotNull String path, @JsonProperty("h") @NotNull String hash) {
            return new WebSocketBody(path, hash);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WebSocketBody)) {
                return false;
            }
            WebSocketBody webSocketBody = (WebSocketBody) other;
            return Intrinsics.areEqual(this.path, webSocketBody.path) && Intrinsics.areEqual(this.hash, webSocketBody.hash);
        }

        public int hashCode() {
            return (this.path.hashCode() * 31) + this.hash.hashCode();
        }

        @NotNull
        public String toString() {
            return "WebSocketBody(path=" + this.path + ", hash=" + this.hash + ')';
        }

        public WebSocketBody(@JsonProperty("p") @NotNull String path, @JsonProperty("h") @NotNull String hash) {
            this.path = path;
            this.hash = hash;
        }

        @NotNull
        public final String getPath() {
            return this.path;
        }

        @NotNull
        public final String getHash() {
            return this.hash;
        }
    }

    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0001\u0010\b\u001a\u00020\u0003\u0012\n\b\u0001\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u000bHÆ\u0003JQ\u0010\u001f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0003\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0014\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010#\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006%"}, d2 = {"Lcom/cncverse/StreamFlixProvider$Episode;", "", "key", "", "link", "", "name", "overview", "runtime", "stillPath", "voteAverage", "", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;D)V", "getKey", "()I", "getLink", "()Ljava/lang/String;", "getName", "getOverview", "getRuntime", "getStillPath", "getVoteAverage", "()D", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "StreamFlixProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Episode {

        @JsonProperty("key")
        private final int key;

        @JsonProperty("link")
        @NotNull
        private final String link;

        @JsonProperty("name")
        @NotNull
        private final String name;

        @JsonProperty("overview")
        @NotNull
        private final String overview;

        @JsonProperty("runtime")
        private final int runtime;

        @JsonProperty("still_path")
        @Nullable
        private final String stillPath;

        @JsonProperty("vote_average")
        private final double voteAverage;

        public static /* synthetic */ Episode copy$default(Episode episode, int i, String str, String str2, String str3, int i2, String str4, double d, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = episode.key;
            }
            if ((i3 & 2) != 0) {
                str = episode.link;
            }
            if ((i3 & 4) != 0) {
                str2 = episode.name;
            }
            if ((i3 & 8) != 0) {
                str3 = episode.overview;
            }
            if ((i3 & 16) != 0) {
                i2 = episode.runtime;
            }
            if ((i3 & 32) != 0) {
                str4 = episode.stillPath;
            }
            if ((i3 & 64) != 0) {
                d = episode.voteAverage;
            }
            double d2 = d;
            int i4 = i2;
            String str5 = str4;
            return episode.copy(i, str, str2, str3, i4, str5, d2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getKey() {
            return this.key;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getLink() {
            return this.link;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getOverview() {
            return this.overview;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getRuntime() {
            return this.runtime;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getStillPath() {
            return this.stillPath;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final double getVoteAverage() {
            return this.voteAverage;
        }

        @NotNull
        public final Episode copy(@JsonProperty("key") int key, @JsonProperty("link") @NotNull String link, @JsonProperty("name") @NotNull String name, @JsonProperty("overview") @NotNull String overview, @JsonProperty("runtime") int runtime, @JsonProperty("still_path") @Nullable String stillPath, @JsonProperty("vote_average") double voteAverage) {
            return new Episode(key, link, name, overview, runtime, stillPath, voteAverage);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Episode)) {
                return false;
            }
            Episode episode = (Episode) other;
            return this.key == episode.key && Intrinsics.areEqual(this.link, episode.link) && Intrinsics.areEqual(this.name, episode.name) && Intrinsics.areEqual(this.overview, episode.overview) && this.runtime == episode.runtime && Intrinsics.areEqual(this.stillPath, episode.stillPath) && Double.compare(this.voteAverage, episode.voteAverage) == 0;
        }

        public int hashCode() {
            return (((((((((((this.key * 31) + this.link.hashCode()) * 31) + this.name.hashCode()) * 31) + this.overview.hashCode()) * 31) + this.runtime) * 31) + (this.stillPath == null ? 0 : this.stillPath.hashCode())) * 31) + StreamFlixProvider$Episode$$ExternalSyntheticBackport0.m(this.voteAverage);
        }

        @NotNull
        public String toString() {
            return "Episode(key=" + this.key + ", link=" + this.link + ", name=" + this.name + ", overview=" + this.overview + ", runtime=" + this.runtime + ", stillPath=" + this.stillPath + ", voteAverage=" + this.voteAverage + ')';
        }

        public Episode(@JsonProperty("key") int key, @JsonProperty("link") @NotNull String link, @JsonProperty("name") @NotNull String name, @JsonProperty("overview") @NotNull String overview, @JsonProperty("runtime") int runtime, @JsonProperty("still_path") @Nullable String stillPath, @JsonProperty("vote_average") double voteAverage) {
            this.key = key;
            this.link = link;
            this.name = name;
            this.overview = overview;
            this.runtime = runtime;
            this.stillPath = stillPath;
            this.voteAverage = voteAverage;
        }

        public final int getKey() {
            return this.key;
        }

        @NotNull
        public final String getLink() {
            return this.link;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final String getOverview() {
            return this.overview;
        }

        public final int getRuntime() {
            return this.runtime;
        }

        @Nullable
        public final String getStillPath() {
            return this.stillPath;
        }

        public final double getVoteAverage() {
            return this.voteAverage;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0153 A[Catch: Exception -> 0x016d, TryCatch #1 {Exception -> 0x016d, blocks: (B:25:0x00d5, B:31:0x0102, B:39:0x0130, B:43:0x0138, B:52:0x0168, B:51:0x0153, B:50:0x014d, B:38:0x0125, B:30:0x00f8, B:22:0x00ce, B:45:0x013e, B:35:0x010a, B:27:0x00df), top: B:64:0x00ce, inners: #2, #3, #4, #7 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x010a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x013e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getConfig(kotlin.coroutines.Continuation<? super com.cncverse.StreamFlixProvider.ConfigResponse> r23) {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.StreamFlixProvider.getConfig(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0139 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0181 A[Catch: Exception -> 0x0346, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b2 A[Catch: Exception -> 0x0346, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0208 A[Catch: Exception -> 0x0346, LOOP:1: B:68:0x0202->B:70:0x0208, LOOP_END, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0273 A[Catch: Exception -> 0x0346, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02c5 A[Catch: Exception -> 0x0346, LOOP:3: B:89:0x02bf->B:91:0x02c5, LOOP_END, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x031c A[Catch: Exception -> 0x0346, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0337 A[Catch: Exception -> 0x0346, TRY_LEAVE, TryCatch #6 {Exception -> 0x0346, blocks: (B:24:0x0105, B:30:0x0132, B:37:0x015f, B:40:0x0166, B:50:0x0196, B:51:0x01ac, B:53:0x01b2, B:55:0x01c1, B:57:0x01c9, B:66:0x01dc, B:67:0x01e0, B:68:0x0202, B:70:0x0208, B:71:0x0252, B:72:0x026d, B:74:0x0273, B:76:0x0282, B:78:0x028a, B:87:0x029d, B:88:0x02a1, B:89:0x02bf, B:91:0x02c5, B:92:0x030b, B:94:0x031c, B:95:0x032e, B:97:0x0337, B:49:0x0181, B:48:0x017b, B:36:0x0154, B:29:0x0128, B:33:0x0139, B:26:0x010f, B:43:0x016c), top: B:116:0x0105, inners: #1, #3, #8 }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getMainPage(int r37, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.MainPageRequest r38, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.HomePageResponse> r39) {
        /*
            Method dump skipped, instruction units count: 952
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.StreamFlixProvider.getMainPage(int, com.lagradost.cloudstream3.MainPageRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$1$0(StreamFlixItem $item, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl("https://image.tmdb.org/t/p/w500/" + $item.getMoviePoster());
        String movieYear = $item.getMovieYear();
        $this$newMovieSearchResponse.setYear(movieYear != null ? StringsKt.toIntOrNull(movieYear) : null);
        $this$newMovieSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$3$0(StreamFlixItem $item, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl("https://image.tmdb.org/t/p/w500/" + $item.getMoviePoster());
        String movieYear = $item.getMovieYear();
        $this$newTvSeriesSearchResponse.setYear(movieYear != null ? StringsKt.toIntOrNull(movieYear) : null);
        $this$newTvSeriesSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMainPage$lambda$4(MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl((String) null);
        $this$newMovieSearchResponse.setYear(2024);
        $this$newMovieSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0123 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x020d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x016c A[Catch: Exception -> 0x02af, TryCatch #1 {Exception -> 0x02af, blocks: (B:24:0x00ee, B:30:0x011b, B:38:0x0149, B:42:0x0151, B:51:0x0181, B:52:0x0197, B:54:0x019d, B:56:0x01ad, B:62:0x01b9, B:64:0x01cd, B:66:0x01d3, B:71:0x01e4, B:73:0x01ea, B:86:0x020a, B:88:0x0212, B:89:0x0223, B:91:0x0229, B:93:0x0238, B:94:0x0271, B:50:0x016c, B:49:0x0166, B:37:0x013e, B:29:0x0111, B:26:0x00f8, B:44:0x0157, B:34:0x0123), top: B:107:0x00ee, inners: #0, #5, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x019d A[Catch: Exception -> 0x02af, TryCatch #1 {Exception -> 0x02af, blocks: (B:24:0x00ee, B:30:0x011b, B:38:0x0149, B:42:0x0151, B:51:0x0181, B:52:0x0197, B:54:0x019d, B:56:0x01ad, B:62:0x01b9, B:64:0x01cd, B:66:0x01d3, B:71:0x01e4, B:73:0x01ea, B:86:0x020a, B:88:0x0212, B:89:0x0223, B:91:0x0229, B:93:0x0238, B:94:0x0271, B:50:0x016c, B:49:0x0166, B:37:0x013e, B:29:0x0111, B:26:0x00f8, B:44:0x0157, B:34:0x0123), top: B:107:0x00ee, inners: #0, #5, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x020a A[Catch: Exception -> 0x02af, TryCatch #1 {Exception -> 0x02af, blocks: (B:24:0x00ee, B:30:0x011b, B:38:0x0149, B:42:0x0151, B:51:0x0181, B:52:0x0197, B:54:0x019d, B:56:0x01ad, B:62:0x01b9, B:64:0x01cd, B:66:0x01d3, B:71:0x01e4, B:73:0x01ea, B:86:0x020a, B:88:0x0212, B:89:0x0223, B:91:0x0229, B:93:0x0238, B:94:0x0271, B:50:0x016c, B:49:0x0166, B:37:0x013e, B:29:0x0111, B:26:0x00f8, B:44:0x0157, B:34:0x0123), top: B:107:0x00ee, inners: #0, #5, #6, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0229 A[Catch: Exception -> 0x02af, TryCatch #1 {Exception -> 0x02af, blocks: (B:24:0x00ee, B:30:0x011b, B:38:0x0149, B:42:0x0151, B:51:0x0181, B:52:0x0197, B:54:0x019d, B:56:0x01ad, B:62:0x01b9, B:64:0x01cd, B:66:0x01d3, B:71:0x01e4, B:73:0x01ea, B:86:0x020a, B:88:0x0212, B:89:0x0223, B:91:0x0229, B:93:0x0238, B:94:0x0271, B:50:0x016c, B:49:0x0166, B:37:0x013e, B:29:0x0111, B:26:0x00f8, B:44:0x0157, B:34:0x0123), top: B:107:0x00ee, inners: #0, #5, #6, #8 }] */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object search(@org.jetbrains.annotations.NotNull java.lang.String r31, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<? extends com.lagradost.cloudstream3.SearchResponse>> r32) {
        /*
            Method dump skipped, instruction units count: 746
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.StreamFlixProvider.search(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$1$0(StreamFlixItem $item, TvSeriesSearchResponse $this$newTvSeriesSearchResponse) {
        $this$newTvSeriesSearchResponse.setPosterUrl("https://image.tmdb.org/t/p/w500/" + $item.getMoviePoster());
        String movieYear = $item.getMovieYear();
        $this$newTvSeriesSearchResponse.setYear(movieYear != null ? StringsKt.toIntOrNull(movieYear) : null);
        $this$newTvSeriesSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit search$lambda$1$1(StreamFlixItem $item, MovieSearchResponse $this$newMovieSearchResponse) {
        $this$newMovieSearchResponse.setPosterUrl("https://image.tmdb.org/t/p/w500/" + $item.getMoviePoster());
        String movieYear = $item.getMovieYear();
        $this$newMovieSearchResponse.setYear(movieYear != null ? StringsKt.toIntOrNull(movieYear) : null);
        $this$newMovieSearchResponse.setQuality(SearchQuality.HD);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0349 A[Catch: Exception -> 0x0502, TRY_LEAVE, TryCatch #18 {Exception -> 0x0502, blocks: (B:50:0x025e, B:56:0x028f, B:63:0x02bc, B:66:0x02c3, B:79:0x02ff, B:80:0x030b, B:87:0x0327, B:89:0x032b, B:98:0x0343, B:100:0x0349, B:118:0x038b, B:78:0x02e6, B:62:0x02b1, B:55:0x0285, B:52:0x026a, B:59:0x0296), top: B:203:0x025e, inners: #2, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0381 A[Catch: Exception -> 0x02df, TRY_LEAVE, TryCatch #9 {Exception -> 0x02df, blocks: (B:82:0x0311, B:92:0x0334, B:103:0x0350, B:105:0x0369, B:107:0x036f, B:109:0x0378, B:113:0x0381, B:74:0x02d8, B:69:0x02c9), top: B:188:0x02c6, inners: #20 }] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x03de A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0447 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0296 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02c9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0326 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02e6 A[Catch: Exception -> 0x0502, TRY_ENTER, TryCatch #18 {Exception -> 0x0502, blocks: (B:50:0x025e, B:56:0x028f, B:63:0x02bc, B:66:0x02c3, B:79:0x02ff, B:80:0x030b, B:87:0x0327, B:89:0x032b, B:98:0x0343, B:100:0x0349, B:118:0x038b, B:78:0x02e6, B:62:0x02b1, B:55:0x0285, B:52:0x026a, B:59:0x0296), top: B:203:0x025e, inners: #2, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0311 A[Catch: Exception -> 0x02df, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x02df, blocks: (B:82:0x0311, B:92:0x0334, B:103:0x0350, B:105:0x0369, B:107:0x036f, B:109:0x0378, B:113:0x0381, B:74:0x02d8, B:69:0x02c9), top: B:188:0x02c6, inners: #20 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x032b A[Catch: Exception -> 0x0502, TRY_LEAVE, TryCatch #18 {Exception -> 0x0502, blocks: (B:50:0x025e, B:56:0x028f, B:63:0x02bc, B:66:0x02c3, B:79:0x02ff, B:80:0x030b, B:87:0x0327, B:89:0x032b, B:98:0x0343, B:100:0x0349, B:118:0x038b, B:78:0x02e6, B:62:0x02b1, B:55:0x0285, B:52:0x026a, B:59:0x0296), top: B:203:0x025e, inners: #2, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0341  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object load(@org.jetbrains.annotations.NotNull java.lang.String r32, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.lagradost.cloudstream3.LoadResponse> r33) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 1392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.StreamFlixProvider.load(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$load$2, reason: invalid class name */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider$load$2", f = "StreamFlixProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass2 = new AnonymousClass2(continuation);
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
                    $this$newMovieLoadResponse.setPlot("The StreamFlix service is currently unavailable. Please try again later.");
                    $this$newMovieLoadResponse.setYear(Boxing.boxInt(2024));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$load$3, reason: invalid class name */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/TvSeriesLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider$load$3", f = "StreamFlixProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nStreamFlixProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamFlixProvider.kt\ncom/cncverse/StreamFlixProvider$load$3\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,777:1\n1#2:778\n*E\n"})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<TvSeriesLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ StreamFlixItem $item;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(StreamFlixItem streamFlixItem, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$item = streamFlixItem;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass3 = new AnonymousClass3(this.$item, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        public final Object invoke(TvSeriesLoadResponse tvSeriesLoadResponse, Continuation<? super Unit> continuation) {
            return create(tvSeriesLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            List listEmptyList;
            TvSeriesLoadResponse $this$newTvSeriesLoadResponse = (TvSeriesLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String it = this.$item.getMoviePoster();
                    $this$newTvSeriesLoadResponse.setPosterUrl(it != null ? "https://image.tmdb.org/t/p/w500/" + it : null);
                    String it2 = this.$item.getMovieBanner();
                    $this$newTvSeriesLoadResponse.setBackgroundPosterUrl(it2 != null ? "https://image.tmdb.org/t/p/original/" + it2 : null);
                    String movieYear = this.$item.getMovieYear();
                    $this$newTvSeriesLoadResponse.setYear(movieYear != null ? StringsKt.toIntOrNull(movieYear) : null);
                    $this$newTvSeriesLoadResponse.setPlot(this.$item.getMovieDesc());
                    String movieInfo = this.$item.getMovieInfo();
                    if (movieInfo == null || (listEmptyList = StringsKt.split$default(movieInfo, new String[]{"/"}, false, 0, 6, (Object) null)) == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    $this$newTvSeriesLoadResponse.setTags(listEmptyList);
                    $this$newTvSeriesLoadResponse.setScore(Score.Companion.from10(Boxing.boxDouble(this.$item.getMovieRating())));
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: renamed from: com.cncverse.StreamFlixProvider$load$4, reason: invalid class name */
    /* JADX INFO: compiled from: StreamFlixProvider.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lcom/lagradost/cloudstream3/MovieLoadResponse;"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.StreamFlixProvider$load$4", f = "StreamFlixProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, nl = {}, s = {}, v = 2)
    @SourceDebugExtension({"SMAP\nStreamFlixProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamFlixProvider.kt\ncom/cncverse/StreamFlixProvider$load$4\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,777:1\n1#2:778\n*E\n"})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<MovieLoadResponse, Continuation<? super Unit>, Object> {
        final /* synthetic */ StreamFlixItem $item;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(StreamFlixItem streamFlixItem, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$item = streamFlixItem;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass4 = new AnonymousClass4(this.$item, continuation);
            anonymousClass4.L$0 = obj;
            return anonymousClass4;
        }

        public final Object invoke(MovieLoadResponse movieLoadResponse, Continuation<? super Unit> continuation) {
            return create(movieLoadResponse, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            List listEmptyList;
            MovieLoadResponse $this$newMovieLoadResponse = (MovieLoadResponse) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    String it = this.$item.getMoviePoster();
                    $this$newMovieLoadResponse.setPosterUrl(it != null ? "https://image.tmdb.org/t/p/w500/" + it : null);
                    String it2 = this.$item.getMovieBanner();
                    $this$newMovieLoadResponse.setBackgroundPosterUrl(it2 != null ? "https://image.tmdb.org/t/p/original/" + it2 : null);
                    String movieYear = this.$item.getMovieYear();
                    $this$newMovieLoadResponse.setYear(movieYear != null ? StringsKt.toIntOrNull(movieYear) : null);
                    $this$newMovieLoadResponse.setPlot(this.$item.getMovieDesc());
                    String movieInfo = this.$item.getMovieInfo();
                    if (movieInfo == null || (listEmptyList = StringsKt.split$default(movieInfo, new String[]{"/"}, false, 0, 6, (Object) null)) == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    $this$newMovieLoadResponse.setTags(listEmptyList);
                    $this$newMovieLoadResponse.setScore(Score.Companion.from10(Boxing.boxDouble(this.$item.getMovieRating())));
                    $this$newMovieLoadResponse.setRecommendations(CollectionsKt.emptyList());
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007e A[Catch: Exception -> 0x0157, TryCatch #3 {Exception -> 0x0157, blocks: (B:21:0x006c, B:22:0x0078, B:24:0x007e, B:25:0x00a1, B:27:0x00a7), top: B:61:0x006c }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x010b A[Catch: Exception -> 0x0155, TryCatch #4 {Exception -> 0x0155, blocks: (B:31:0x00d7, B:35:0x00ff, B:37:0x010b, B:43:0x011b), top: B:63:0x00d7 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getEpisodesFromWebSocket(java.lang.String r25, int r26, kotlin.coroutines.Continuation<? super java.util.List<com.lagradost.cloudstream3.Episode>> r27) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.StreamFlixProvider.getEpisodesFromWebSocket(java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object getEpisodesFromWebSocket$default(StreamFlixProvider streamFlixProvider, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 1;
        }
        return streamFlixProvider.getEpisodesFromWebSocket(str, i, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getEpisodesFromWebSocket$lambda$0$0$0(StreamFlixWebSocketExtractor.EpisodeData $episodeData, int $seasonNumber, int $episodeKey, com.lagradost.cloudstream3.Episode $this$newEpisode) {
        $this$newEpisode.setName($episodeData.getName());
        $this$newEpisode.setSeason(Integer.valueOf($seasonNumber));
        $this$newEpisode.setEpisode(Integer.valueOf($episodeKey + 1));
        $this$newEpisode.setDescription($episodeData.getOverview());
        String it = $episodeData.getStillPath();
        $this$newEpisode.setPosterUrl(it != null ? "https://image.tmdb.org/t/p/w500/" + it : null);
        $this$newEpisode.setScore(Score.Companion.from10(Double.valueOf($episodeData.getVoteAverage())));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getEpisodesFromWebSocket$lambda$1(int $episode, int $season, com.lagradost.cloudstream3.Episode $this$newEpisode) {
        $this$newEpisode.setName("Episode " + $episode);
        $this$newEpisode.setSeason(Integer.valueOf($season));
        $this$newEpisode.setEpisode(Integer.valueOf($episode));
        $this$newEpisode.setDescription("Episode " + $episode + " of Season " + $season);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Not initialized variable reg: 20, insn: 0x027b: MOVE (r3 I:??[OBJECT, ARRAY]) = (r20 I:??[OBJECT, ARRAY] A[D('str' java.lang.String)]), block:B:34:0x0271 */
    /* JADX WARN: Removed duplicated region for block: B:103:0x03da A[Catch: Exception -> 0x0628, TRY_LEAVE, TryCatch #16 {Exception -> 0x0628, blocks: (B:101:0x03d4, B:103:0x03da), top: B:309:0x03d4 }] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x04e7 A[Catch: Exception -> 0x0601, TRY_LEAVE, TryCatch #41 {Exception -> 0x0601, blocks: (B:124:0x04e1, B:126:0x04e7), top: B:359:0x04e1 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x05e9  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0718 A[Catch: Exception -> 0x088d, TRY_LEAVE, TryCatch #28 {Exception -> 0x088d, blocks: (B:169:0x0712, B:171:0x0718), top: B:333:0x0712 }] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0878  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x08e3 A[Catch: Exception -> 0x0af0, TRY_LEAVE, TryCatch #1 {Exception -> 0x0af0, blocks: (B:210:0x08dd, B:212:0x08e3), top: B:280:0x08dd }] */
    /* JADX WARN: Removed duplicated region for block: B:226:0x09a9  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x09cc A[Catch: Exception -> 0x0ad4, TRY_LEAVE, TryCatch #32 {Exception -> 0x0ad4, blocks: (B:229:0x09c6, B:231:0x09cc), top: B:341:0x09c6 }] */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0ac8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x039a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:112:0x0469 -> B:323:0x047c). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:135:0x057a -> B:317:0x058e). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:182:0x07eb -> B:291:0x080a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:219:0x096f -> B:325:0x0980). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:240:0x0a6d -> B:345:0x0a83). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r37, boolean r38, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r39, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r40, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r41) {
        /*
            Method dump skipped, instruction units count: 2988
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.StreamFlixProvider.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
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
