package com.Tamilian;

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
import com.lagradost.cloudstream3.HomePageResponse;
import com.lagradost.cloudstream3.MainPageRequest;
import com.lagradost.cloudstream3.TvType;
import com.lagradost.cloudstream3.metaproviders.TmdbLink;
import com.lagradost.cloudstream3.metaproviders.TmdbProvider;
import com.lagradost.cloudstream3.ui.settings.Globals;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: Tamilian.kt */
/* JADX INFO: loaded from: classes.dex */
@SourceDebugExtension({"SMAP\nTamilian.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Tamilian.kt\ncom/Tamilian/Tamilian\n+ 2 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n+ 5 NiceResponse.kt\ncom/lagradost/nicehttp/NiceResponse\n*L\n1#1,402:1\n63#2:403\n64#2,15:405\n1#3:404\n1#3:422\n50#4:420\n43#4:421\n67#5,5:423\n*S KotlinDebug\n*F\n+ 1 Tamilian.kt\ncom/Tamilian/Tamilian\n*L\n81#1:403\n81#1:405,15\n81#1:404\n81#1:420\n81#1:421\n88#1:423,5\n*E\n"})
public final class Tamilian extends TmdbProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final String HOST = "https://embedojo.net";

    @NotNull

    @Nullable
    private static Context context;

    @NotNull
    private String name = "Tamilian";
    private final boolean hasMainPage = true;

    @NotNull
    private String lang = "ta";
    private final boolean instantLinkLoading = true;
    private final boolean useMetaLoadResponse = true;
    private final boolean hasQuickSearch = true;

    @NotNull
    private final Set<TvType> supportedTypes = SetsKt.setOf(TvType.Movie);

    /* JADX INFO: renamed from: com.Tamilian.Tamilian$loadLinks$1, reason: invalid class name */
    /* JADX INFO: compiled from: Tamilian.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.Tamilian.Tamilian", f = "Tamilian.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {82, 87, 91}, m = "loadLinks", n = {"data", "subtitleCallback", "callback", "mediaData", "isCasting", "data", "subtitleCallback", "callback", "mediaData", "script", "token", "isCasting", "data", "subtitleCallback", "callback", "mediaData", "script", "token", "m3u8", "headers", "it", "isCasting", "$i$a$-let-Tamilian$loadLinks$3"}, nl = {83, 88, 104}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "Z$0", "I$0"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return Tamilian.this.loadLinks(null, false, null, null, (Continuation) this);
        }
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

    public boolean getInstantLinkLoading() {
        return this.instantLinkLoading;
    }

    public boolean getUseMetaLoadResponse() {
        return this.useMetaLoadResponse;
    }

    public boolean getHasQuickSearch() {
        return this.hasQuickSearch;
    }

    @NotNull
    public Set<TvType> getSupportedTypes() {
        return this.supportedTypes;
    }

    /* JADX INFO: compiled from: Tamilian.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/Tamilian/Tamilian$Companion;", "", "<init>", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "HOST", "", "OMG10", "lastBrowserOpenMs", "", "telegramPopupShown", "", "subscriptionPopupShown", "BROWSER_DEBOUNCE_MS", "Tamilian_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final Context getContext() {
            return Tamilian.context;
        }

        public final void setContext(@Nullable Context context) {
            Tamilian.context = context;
        }
    }

    @Nullable
    public Object getMainPage(int page, @NotNull MainPageRequest request, @NotNull Continuation<? super HomePageResponse> continuation) {
        showTelegramPopup();
        showSubscriptionPopupIfNeeded();
        return super.getMainPage(page, request, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0231 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0301 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03af  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object loadLinks(@org.jetbrains.annotations.NotNull java.lang.String r36, boolean r37, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r38, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r39, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r40) {
        /*
            Method dump skipped, instruction units count: 964
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Tamilian.Tamilian.loadLinks(java.lang.String, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void loadLinks$lambda$0$0(Context $_ctx) {
        Toast.makeText($_ctx, "⚠️(Opening ads) Subscription expired. If you have renewed your subscription, please re-verify it in Subscription Manager.", 1).show();
    }

    private final LinkData toLinkData(TmdbLink $this$toLinkData) {
        return new LinkData(null, null, $this$toLinkData.getImdbID(), $this$toLinkData.getTmdbID(), null, null, $this$toLinkData.getSeason(), $this$toLinkData.getEpisode(), null, null, $this$toLinkData.getMovieName(), null, null, false, null, null, null, null, null, null, false, false, false, 8387379, null);
    }

    /* JADX INFO: compiled from: Tamilian.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b@\b\u0086\b\u0018\u00002\u00020\u0001B\u0093\u0002\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0003\u0010\u0019\u001a\u00020\u0012\u0012\b\b\u0003\u0010\u001a\u001a\u00020\u0012\u0012\b\b\u0003\u0010\u001b\u001a\u00020\u0012¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u00105\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010A\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010B\u001a\u00020\u0012HÆ\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u0010\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001fJ\u000b\u0010E\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010I\u001a\u00020\u0012HÆ\u0003J\t\u0010J\u001a\u00020\u0012HÆ\u0003J\t\u0010K\u001a\u00020\u0012HÆ\u0003J\u009a\u0002\u0010L\u001a\u00020\u00002\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0011\u001a\u00020\u00122\n\b\u0003\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0003\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\b\u0003\u0010\u0019\u001a\u00020\u00122\b\b\u0003\u0010\u001a\u001a\u00020\u00122\b\b\u0003\u0010\u001b\u001a\u00020\u0012HÆ\u0001¢\u0006\u0002\u0010MJ\u0014\u0010N\u001a\u00020\u00122\b\u0010O\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010P\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010Q\u001a\u00020\u0006HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b!\u0010\u001fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b$\u0010\u001fR\u001a\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b%\u0010\u001fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010#R\u001a\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b'\u0010\u001fR\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b(\u0010\u001fR\u0018\u0010\f\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b,\u0010\u001fR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010#R\u0016\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010.R\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b/\u0010\u001fR\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b0\u0010\u001fR\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010#R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010#R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010#R\u0016\u0010\u0019\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010.R\u0016\u0010\u001a\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010.R\u0016\u0010\u001b\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010.¨\u0006R"}, d2 = {"Lcom/Tamilian/Tamilian$LinkData;", "", "simklId", "", "traktId", "imdbId", "", "tmdbId", "tvdbId", "type", "season", "episode", "aniId", "malId", "title", "year", "orgTitle", "isAnime", "", "airedYear", "lastSeason", "epsTitle", "jpTitle", "date", "airedDate", "isAsian", "isBollywood", "isCartoon", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)V", "getSimklId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTraktId", "getImdbId", "()Ljava/lang/String;", "getTmdbId", "getTvdbId", "getType", "getSeason", "getEpisode", "getAniId", "getMalId", "getTitle", "getYear", "getOrgTitle", "()Z", "getAiredYear", "getLastSeason", "getEpsTitle", "getJpTitle", "getDate", "getAiredDate", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)Lcom/Tamilian/Tamilian$LinkData;", "equals", "other", "hashCode", "toString", "Tamilian_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class LinkData {

        @JsonProperty("airedDate")
        @Nullable
        private final String airedDate;

        @JsonProperty("airedYear")
        @Nullable
        private final Integer airedYear;

        @JsonProperty("aniId")
        @Nullable
        private final String aniId;

        @JsonProperty("date")
        @Nullable
        private final String date;

        @JsonProperty("episode")
        @Nullable
        private final Integer episode;

        @JsonProperty("epsTitle")
        @Nullable
        private final String epsTitle;

        @JsonProperty("imdbId")
        @Nullable
        private final String imdbId;

        @JsonProperty("isAnime")
        private final boolean isAnime;

        @JsonProperty("isAsian")
        private final boolean isAsian;

        @JsonProperty("isBollywood")
        private final boolean isBollywood;

        @JsonProperty("isCartoon")
        private final boolean isCartoon;

        @JsonProperty("jpTitle")
        @Nullable
        private final String jpTitle;

        @JsonProperty("lastSeason")
        @Nullable
        private final Integer lastSeason;

        @JsonProperty("malId")
        @Nullable
        private final String malId;

        @JsonProperty("orgTitle")
        @Nullable
        private final String orgTitle;

        @JsonProperty("season")
        @Nullable
        private final Integer season;

        @JsonProperty("simklId")
        @Nullable
        private final Integer simklId;

        @JsonProperty("title")
        @Nullable
        private final String title;

        @JsonProperty("tmdbId")
        @Nullable
        private final Integer tmdbId;

        @JsonProperty("traktId")
        @Nullable
        private final Integer traktId;

        @JsonProperty("tvdbId")
        @Nullable
        private final Integer tvdbId;

        @JsonProperty("type")
        @Nullable
        private final String type;

        @JsonProperty("year")
        @Nullable
        private final Integer year;

        public LinkData() {
            this(null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, false, false, false, 8388607, null);
        }

        public static /* synthetic */ LinkData copy$default(LinkData linkData, Integer num, Integer num2, String str, Integer num3, Integer num4, String str2, Integer num5, Integer num6, String str3, String str4, String str5, Integer num7, String str6, boolean z, Integer num8, Integer num9, String str7, String str8, String str9, String str10, boolean z2, boolean z3, boolean z4, int i, Object obj) {
            boolean z5;
            boolean z6;
            Integer num10 = (i & 1) != 0 ? linkData.simklId : num;
            Integer num11 = (i & 2) != 0 ? linkData.traktId : num2;
            String str11 = (i & 4) != 0 ? linkData.imdbId : str;
            Integer num12 = (i & 8) != 0 ? linkData.tmdbId : num3;
            Integer num13 = (i & 16) != 0 ? linkData.tvdbId : num4;
            String str12 = (i & 32) != 0 ? linkData.type : str2;
            Integer num14 = (i & 64) != 0 ? linkData.season : num5;
            Integer num15 = (i & 128) != 0 ? linkData.episode : num6;
            String str13 = (i & 256) != 0 ? linkData.aniId : str3;
            String str14 = (i & 512) != 0 ? linkData.malId : str4;
            String str15 = (i & 1024) != 0 ? linkData.title : str5;
            Integer num16 = (i & 2048) != 0 ? linkData.year : num7;
            String str16 = (i & 4096) != 0 ? linkData.orgTitle : str6;
            boolean z7 = (i & 8192) != 0 ? linkData.isAnime : z;
            Integer num17 = num10;
            Integer num18 = (i & 16384) != 0 ? linkData.airedYear : num8;
            Integer num19 = (i & 32768) != 0 ? linkData.lastSeason : num9;
            String str17 = (i & 65536) != 0 ? linkData.epsTitle : str7;
            String str18 = (i & 131072) != 0 ? linkData.jpTitle : str8;
            String str19 = (i & 262144) != 0 ? linkData.date : str9;
            String str20 = (i & 524288) != 0 ? linkData.airedDate : str10;
            boolean z8 = (i & 1048576) != 0 ? linkData.isAsian : z2;
            boolean z9 = (i & 2097152) != 0 ? linkData.isBollywood : z3;
            if ((i & 4194304) != 0) {
                z6 = z9;
                z5 = linkData.isCartoon;
            } else {
                z5 = z4;
                z6 = z9;
            }
            return linkData.copy(num17, num11, str11, num12, num13, str12, num14, num15, str13, str14, str15, num16, str16, z7, num18, num19, str17, str18, str19, str20, z8, z6, z5);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getSimklId() {
            return this.simklId;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getMalId() {
            return this.malId;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getOrgTitle() {
            return this.orgTitle;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final boolean getIsAnime() {
            return this.isAnime;
        }

        @Nullable
        /* JADX INFO: renamed from: component15, reason: from getter */
        public final Integer getAiredYear() {
            return this.airedYear;
        }

        @Nullable
        /* JADX INFO: renamed from: component16, reason: from getter */
        public final Integer getLastSeason() {
            return this.lastSeason;
        }

        @Nullable
        /* JADX INFO: renamed from: component17, reason: from getter */
        public final String getEpsTitle() {
            return this.epsTitle;
        }

        @Nullable
        /* JADX INFO: renamed from: component18, reason: from getter */
        public final String getJpTitle() {
            return this.jpTitle;
        }

        @Nullable
        /* JADX INFO: renamed from: component19, reason: from getter */
        public final String getDate() {
            return this.date;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTraktId() {
            return this.traktId;
        }

        @Nullable
        /* JADX INFO: renamed from: component20, reason: from getter */
        public final String getAiredDate() {
            return this.airedDate;
        }

        /* JADX INFO: renamed from: component21, reason: from getter */
        public final boolean getIsAsian() {
            return this.isAsian;
        }

        /* JADX INFO: renamed from: component22, reason: from getter */
        public final boolean getIsBollywood() {
            return this.isBollywood;
        }

        /* JADX INFO: renamed from: component23, reason: from getter */
        public final boolean getIsCartoon() {
            return this.isCartoon;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getTvdbId() {
            return this.tvdbId;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Integer getSeason() {
            return this.season;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final Integer getEpisode() {
            return this.episode;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getAniId() {
            return this.aniId;
        }

        @NotNull
        public final LinkData copy(@JsonProperty("simklId") @Nullable Integer simklId, @JsonProperty("traktId") @Nullable Integer traktId, @JsonProperty("imdbId") @Nullable String imdbId, @JsonProperty("tmdbId") @Nullable Integer tmdbId, @JsonProperty("tvdbId") @Nullable Integer tvdbId, @JsonProperty("type") @Nullable String type, @JsonProperty("season") @Nullable Integer season, @JsonProperty("episode") @Nullable Integer episode, @JsonProperty("aniId") @Nullable String aniId, @JsonProperty("malId") @Nullable String malId, @JsonProperty("title") @Nullable String title, @JsonProperty("year") @Nullable Integer year, @JsonProperty("orgTitle") @Nullable String orgTitle, @JsonProperty("isAnime") boolean isAnime, @JsonProperty("airedYear") @Nullable Integer airedYear, @JsonProperty("lastSeason") @Nullable Integer lastSeason, @JsonProperty("epsTitle") @Nullable String epsTitle, @JsonProperty("jpTitle") @Nullable String jpTitle, @JsonProperty("date") @Nullable String date, @JsonProperty("airedDate") @Nullable String airedDate, @JsonProperty("isAsian") boolean isAsian, @JsonProperty("isBollywood") boolean isBollywood, @JsonProperty("isCartoon") boolean isCartoon) {
            return new LinkData(simklId, traktId, imdbId, tmdbId, tvdbId, type, season, episode, aniId, malId, title, year, orgTitle, isAnime, airedYear, lastSeason, epsTitle, jpTitle, date, airedDate, isAsian, isBollywood, isCartoon);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LinkData)) {
                return false;
            }
            LinkData linkData = (LinkData) other;
            return Intrinsics.areEqual(this.simklId, linkData.simklId) && Intrinsics.areEqual(this.traktId, linkData.traktId) && Intrinsics.areEqual(this.imdbId, linkData.imdbId) && Intrinsics.areEqual(this.tmdbId, linkData.tmdbId) && Intrinsics.areEqual(this.tvdbId, linkData.tvdbId) && Intrinsics.areEqual(this.type, linkData.type) && Intrinsics.areEqual(this.season, linkData.season) && Intrinsics.areEqual(this.episode, linkData.episode) && Intrinsics.areEqual(this.aniId, linkData.aniId) && Intrinsics.areEqual(this.malId, linkData.malId) && Intrinsics.areEqual(this.title, linkData.title) && Intrinsics.areEqual(this.year, linkData.year) && Intrinsics.areEqual(this.orgTitle, linkData.orgTitle) && this.isAnime == linkData.isAnime && Intrinsics.areEqual(this.airedYear, linkData.airedYear) && Intrinsics.areEqual(this.lastSeason, linkData.lastSeason) && Intrinsics.areEqual(this.epsTitle, linkData.epsTitle) && Intrinsics.areEqual(this.jpTitle, linkData.jpTitle) && Intrinsics.areEqual(this.date, linkData.date) && Intrinsics.areEqual(this.airedDate, linkData.airedDate) && this.isAsian == linkData.isAsian && this.isBollywood == linkData.isBollywood && this.isCartoon == linkData.isCartoon;
        }

        public int hashCode() {
            return ((((((((((((((((((((((((((((((((((((((((((((this.simklId == null ? 0 : this.simklId.hashCode()) * 31) + (this.traktId == null ? 0 : this.traktId.hashCode())) * 31) + (this.imdbId == null ? 0 : this.imdbId.hashCode())) * 31) + (this.tmdbId == null ? 0 : this.tmdbId.hashCode())) * 31) + (this.tvdbId == null ? 0 : this.tvdbId.hashCode())) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + (this.season == null ? 0 : this.season.hashCode())) * 31) + (this.episode == null ? 0 : this.episode.hashCode())) * 31) + (this.aniId == null ? 0 : this.aniId.hashCode())) * 31) + (this.malId == null ? 0 : this.malId.hashCode())) * 31) + (this.title == null ? 0 : this.title.hashCode())) * 31) + (this.year == null ? 0 : this.year.hashCode())) * 31) + (this.orgTitle == null ? 0 : this.orgTitle.hashCode())) * 31) + Tamilian$LinkData$$ExternalSyntheticBackport0.m(this.isAnime)) * 31) + (this.airedYear == null ? 0 : this.airedYear.hashCode())) * 31) + (this.lastSeason == null ? 0 : this.lastSeason.hashCode())) * 31) + (this.epsTitle == null ? 0 : this.epsTitle.hashCode())) * 31) + (this.jpTitle == null ? 0 : this.jpTitle.hashCode())) * 31) + (this.date == null ? 0 : this.date.hashCode())) * 31) + (this.airedDate != null ? this.airedDate.hashCode() : 0)) * 31) + Tamilian$LinkData$$ExternalSyntheticBackport0.m(this.isAsian)) * 31) + Tamilian$LinkData$$ExternalSyntheticBackport0.m(this.isBollywood)) * 31) + Tamilian$LinkData$$ExternalSyntheticBackport0.m(this.isCartoon);
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("LinkData(simklId=").append(this.simklId).append(", traktId=").append(this.traktId).append(", imdbId=").append(this.imdbId).append(", tmdbId=").append(this.tmdbId).append(", tvdbId=").append(this.tvdbId).append(", type=").append(this.type).append(", season=").append(this.season).append(", episode=").append(this.episode).append(", aniId=").append(this.aniId).append(", malId=").append(this.malId).append(", title=").append(this.title).append(", year=");
            sb.append(this.year).append(", orgTitle=").append(this.orgTitle).append(", isAnime=").append(this.isAnime).append(", airedYear=").append(this.airedYear).append(", lastSeason=").append(this.lastSeason).append(", epsTitle=").append(this.epsTitle).append(", jpTitle=").append(this.jpTitle).append(", date=").append(this.date).append(", airedDate=").append(this.airedDate).append(", isAsian=").append(this.isAsian).append(", isBollywood=").append(this.isBollywood).append(", isCartoon=").append(this.isCartoon);
            sb.append(')');
            return sb.toString();
        }

        public LinkData(@JsonProperty("simklId") @Nullable Integer simklId, @JsonProperty("traktId") @Nullable Integer traktId, @JsonProperty("imdbId") @Nullable String imdbId, @JsonProperty("tmdbId") @Nullable Integer tmdbId, @JsonProperty("tvdbId") @Nullable Integer tvdbId, @JsonProperty("type") @Nullable String type, @JsonProperty("season") @Nullable Integer season, @JsonProperty("episode") @Nullable Integer episode, @JsonProperty("aniId") @Nullable String aniId, @JsonProperty("malId") @Nullable String malId, @JsonProperty("title") @Nullable String title, @JsonProperty("year") @Nullable Integer year, @JsonProperty("orgTitle") @Nullable String orgTitle, @JsonProperty("isAnime") boolean isAnime, @JsonProperty("airedYear") @Nullable Integer airedYear, @JsonProperty("lastSeason") @Nullable Integer lastSeason, @JsonProperty("epsTitle") @Nullable String epsTitle, @JsonProperty("jpTitle") @Nullable String jpTitle, @JsonProperty("date") @Nullable String date, @JsonProperty("airedDate") @Nullable String airedDate, @JsonProperty("isAsian") boolean isAsian, @JsonProperty("isBollywood") boolean isBollywood, @JsonProperty("isCartoon") boolean isCartoon) {
            this.simklId = simklId;
            this.traktId = traktId;
            this.imdbId = imdbId;
            this.tmdbId = tmdbId;
            this.tvdbId = tvdbId;
            this.type = type;
            this.season = season;
            this.episode = episode;
            this.aniId = aniId;
            this.malId = malId;
            this.title = title;
            this.year = year;
            this.orgTitle = orgTitle;
            this.isAnime = isAnime;
            this.airedYear = airedYear;
            this.lastSeason = lastSeason;
            this.epsTitle = epsTitle;
            this.jpTitle = jpTitle;
            this.date = date;
            this.airedDate = airedDate;
            this.isAsian = isAsian;
            this.isBollywood = isBollywood;
            this.isCartoon = isCartoon;
        }

        public /* synthetic */ LinkData(Integer num, Integer num2, String str, Integer num3, Integer num4, String str2, Integer num5, Integer num6, String str3, String str4, String str5, Integer num7, String str6, boolean z, Integer num8, Integer num9, String str7, String str8, String str9, String str10, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : num5, (i & 128) != 0 ? null : num6, (i & 256) != 0 ? null : str3, (i & 512) != 0 ? null : str4, (i & 1024) != 0 ? null : str5, (i & 2048) != 0 ? null : num7, (i & 4096) != 0 ? null : str6, (i & 8192) != 0 ? false : z, (i & 16384) != 0 ? null : num8, (i & 32768) != 0 ? null : num9, (i & 65536) != 0 ? null : str7, (i & 131072) != 0 ? null : str8, (i & 262144) != 0 ? null : str9, (i & 524288) != 0 ? null : str10, (i & 1048576) != 0 ? false : z2, (i & 2097152) != 0 ? false : z3, (i & 4194304) == 0 ? z4 : false);
        }

        @Nullable
        public final Integer getSimklId() {
            return this.simklId;
        }

        @Nullable
        public final Integer getTraktId() {
            return this.traktId;
        }

        @Nullable
        public final String getImdbId() {
            return this.imdbId;
        }

        @Nullable
        public final Integer getTmdbId() {
            return this.tmdbId;
        }

        @Nullable
        public final Integer getTvdbId() {
            return this.tvdbId;
        }

        @Nullable
        public final String getType() {
            return this.type;
        }

        @Nullable
        public final Integer getSeason() {
            return this.season;
        }

        @Nullable
        public final Integer getEpisode() {
            return this.episode;
        }

        @Nullable
        public final String getAniId() {
            return this.aniId;
        }

        @Nullable
        public final String getMalId() {
            return this.malId;
        }

        @Nullable
        public final String getTitle() {
            return this.title;
        }

        @Nullable
        public final Integer getYear() {
            return this.year;
        }

        @Nullable
        public final String getOrgTitle() {
            return this.orgTitle;
        }

        public final boolean isAnime() {
            return this.isAnime;
        }

        @Nullable
        public final Integer getAiredYear() {
            return this.airedYear;
        }

        @Nullable
        public final Integer getLastSeason() {
            return this.lastSeason;
        }

        @Nullable
        public final String getEpsTitle() {
            return this.epsTitle;
        }

        @Nullable
        public final String getJpTitle() {
            return this.jpTitle;
        }

        @Nullable
        public final String getDate() {
            return this.date;
        }

        @Nullable
        public final String getAiredDate() {
            return this.airedDate;
        }

        public final boolean isAsian() {
            return this.isAsian;
        }

        public final boolean isBollywood() {
            return this.isBollywood;
        }

        public final boolean isCartoon() {
            return this.isCartoon;
        }
    }

    /* JADX INFO: compiled from: Tamilian.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u0011\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tHÆ\u0003J\u0011\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J_\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u0010\b\u0002\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010 \u001a\u00020\u00032\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020#HÖ\u0081\u0004J\n\u0010$\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006%"}, d2 = {"Lcom/Tamilian/Tamilian$VideoData;", "", "hls", "", "videoImage", "", "videoSource", "securedLink", "downloadLinks", "", "attachmentLinks", "ck", "<init>", "(ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getHls", "()Z", "getVideoImage", "()Ljava/lang/String;", "getVideoSource", "getSecuredLink", "getDownloadLinks", "()Ljava/util/List;", "getAttachmentLinks", "getCk", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "Tamilian_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VideoData {

        @NotNull
        private final List<Object> attachmentLinks;

        @NotNull
        private final String ck;

        @NotNull
        private final List<Object> downloadLinks;
        private final boolean hls;

        @NotNull
        private final String securedLink;

        @NotNull
        private final String videoImage;

        @NotNull
        private final String videoSource;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VideoData copy$default(VideoData videoData, boolean z, String str, String str2, String str3, List list, List list2, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                z = videoData.hls;
            }
            if ((i & 2) != 0) {
                str = videoData.videoImage;
            }
            if ((i & 4) != 0) {
                str2 = videoData.videoSource;
            }
            if ((i & 8) != 0) {
                str3 = videoData.securedLink;
            }
            if ((i & 16) != 0) {
                list = videoData.downloadLinks;
            }
            if ((i & 32) != 0) {
                list2 = videoData.attachmentLinks;
            }
            if ((i & 64) != 0) {
                str4 = videoData.ck;
            }
            List list3 = list2;
            String str5 = str4;
            List list4 = list;
            String str6 = str2;
            return videoData.copy(z, str, str6, str3, list4, list3, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getHls() {
            return this.hls;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVideoImage() {
            return this.videoImage;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVideoSource() {
            return this.videoSource;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSecuredLink() {
            return this.securedLink;
        }

        @NotNull
        public final List<Object> component5() {
            return this.downloadLinks;
        }

        @NotNull
        public final List<Object> component6() {
            return this.attachmentLinks;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getCk() {
            return this.ck;
        }

        @NotNull
        public final VideoData copy(boolean hls, @NotNull String videoImage, @NotNull String videoSource, @NotNull String securedLink, @NotNull List<? extends Object> downloadLinks, @NotNull List<? extends Object> attachmentLinks, @NotNull String ck) {
            return new VideoData(hls, videoImage, videoSource, securedLink, downloadLinks, attachmentLinks, ck);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoData)) {
                return false;
            }
            VideoData videoData = (VideoData) other;
            return this.hls == videoData.hls && Intrinsics.areEqual(this.videoImage, videoData.videoImage) && Intrinsics.areEqual(this.videoSource, videoData.videoSource) && Intrinsics.areEqual(this.securedLink, videoData.securedLink) && Intrinsics.areEqual(this.downloadLinks, videoData.downloadLinks) && Intrinsics.areEqual(this.attachmentLinks, videoData.attachmentLinks) && Intrinsics.areEqual(this.ck, videoData.ck);
        }

        public int hashCode() {
            return (((((((((((Tamilian$VideoData$$ExternalSyntheticBackport0.m(this.hls) * 31) + this.videoImage.hashCode()) * 31) + this.videoSource.hashCode()) * 31) + this.securedLink.hashCode()) * 31) + this.downloadLinks.hashCode()) * 31) + this.attachmentLinks.hashCode()) * 31) + this.ck.hashCode();
        }

        @NotNull
        public String toString() {
            return "VideoData(hls=" + this.hls + ", videoImage=" + this.videoImage + ", videoSource=" + this.videoSource + ", securedLink=" + this.securedLink + ", downloadLinks=" + this.downloadLinks + ", attachmentLinks=" + this.attachmentLinks + ", ck=" + this.ck + ')';
        }

        public VideoData(boolean hls, @NotNull String videoImage, @NotNull String videoSource, @NotNull String securedLink, @NotNull List<? extends Object> list, @NotNull List<? extends Object> list2, @NotNull String ck) {
            this.hls = hls;
            this.videoImage = videoImage;
            this.videoSource = videoSource;
            this.securedLink = securedLink;
            this.downloadLinks = list;
            this.attachmentLinks = list2;
            this.ck = ck;
        }

        public final boolean getHls() {
            return this.hls;
        }

        @NotNull
        public final String getVideoImage() {
            return this.videoImage;
        }

        @NotNull
        public final String getVideoSource() {
            return this.videoSource;
        }

        @NotNull
        public final String getSecuredLink() {
            return this.securedLink;
        }

        @NotNull
        public final List<Object> getDownloadLinks() {
            return this.downloadLinks;
        }

        @NotNull
        public final List<Object> getAttachmentLinks() {
            return this.attachmentLinks;
        }

        @NotNull
        public final String getCk() {
            return this.ck;
        }
    }


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */

    /* JADX INFO: Access modifiers changed from: private */


    /* JADX INFO: Access modifiers changed from: private */
}
