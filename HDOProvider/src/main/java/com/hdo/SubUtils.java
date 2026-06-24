package com.hdo;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: SubUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0002JF\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eH\u0086@¢\u0006\u0002\u0010\u0010JF\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eH\u0086@¢\u0006\u0002\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/hdo/SubUtils;", "", "<init>", "()V", "getLanguage", "", "code", "invokeSubtitleAPI", "", "id", "season", "", "episode", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invokeWyZIESUBAPI", "HDOProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSubUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SubUtils.kt\ncom/hdo/SubUtils\n+ 2 NiceResponse.kt\ncom/lagradost/nicehttp/NiceResponse\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 AppUtils.kt\ncom/lagradost/cloudstream3/utils/AppUtils\n+ 6 Extensions.kt\ncom/fasterxml/jackson/module/kotlin/ExtensionsKt\n*L\n1#1,113:1\n67#2,5:114\n1586#3:119\n1661#3,2:120\n1663#3:123\n1586#3:147\n1661#3,3:148\n1#4:122\n1#4:127\n93#5,2:124\n63#5:126\n64#5,15:128\n95#5,2:145\n50#6:143\n43#6:144\n*S KotlinDebug\n*F\n+ 1 SubUtils.kt\ncom/hdo/SubUtils\n*L\n73#1:114,5\n73#1:119\n73#1:120,2\n73#1:123\n101#1:147\n101#1:148,3\n100#1:127\n100#1:124,2\n100#1:126\n100#1:128,15\n100#1:145,2\n100#1:143\n100#1:144\n*E\n"})
public final class SubUtils {

    @NotNull
    public static final SubUtils INSTANCE = new SubUtils();

    /* JADX INFO: renamed from: com.hdo.SubUtils$invokeSubtitleAPI$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.SubUtils", f = "SubUtils.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {72, 77}, m = "invokeSubtitleAPI", n = {"id", "season", "episode", "subtitleCallback", "url", "headers", "id", "season", "episode", "subtitleCallback", "url", "headers", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "suburl", "lan", "$i$f$map", "$i$f$mapTo", "$i$a$-map-SubUtils$invokeSubtitleAPI$2"}, nl = {73, 76}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$10", "L$11", "L$12", "L$13", "I$0", "I$1", "I$2"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
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

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubUtils.this.invokeSubtitleAPI(null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.hdo.SubUtils$invokeWyZIESUBAPI$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SubUtils.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.hdo.SubUtils", f = "SubUtils.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {99, 105}, m = "invokeWyZIESUBAPI", n = {"id", "season", "episode", "subtitleCallback", "WyZIESUBAPI", "url", "id", "season", "episode", "subtitleCallback", "WyZIESUBAPI", "url", "res", "subtitles", "$this$map$iv", "$this$mapTo$iv$iv", "destination$iv$iv", "item$iv$iv", "it", "suburl", "lan", "$i$f$map", "$i$f$mapTo", "$i$a$-map-SubUtils$invokeWyZIESUBAPI$2"}, nl = {100, 104}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$12", "L$13", "L$14", "L$15", "I$0", "I$1", "I$2"}, v = 2)
    static final class C00021 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
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
            return SubUtils.this.invokeWyZIESUBAPI(null, null, null, null, (Continuation) this);
        }
    }

    private SubUtils() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final String getLanguage(String code) {
        String lowerCase;
        if (code != null) {
            lowerCase = code.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        } else {
            lowerCase = null;
        }
        if (lowerCase != null) {
            switch (lowerCase.hashCode()) {
                case 3121:
                    if (lowerCase.equals("ar")) {
                        return "Arabic";
                    }
                    break;
                case 3201:
                    if (lowerCase.equals("de")) {
                        return "German";
                    }
                    break;
                case 3241:
                    if (lowerCase.equals("en")) {
                        return "English";
                    }
                    break;
                case 3246:
                    if (lowerCase.equals("es")) {
                        return "Spanish";
                    }
                    break;
                case 3276:
                    if (lowerCase.equals("fr")) {
                        return "French";
                    }
                    break;
                case 3329:
                    if (lowerCase.equals("hi")) {
                        return "Hindi";
                    }
                    break;
                case 3371:
                    if (lowerCase.equals("it")) {
                        return "Italian";
                    }
                    break;
                case 3383:
                    if (lowerCase.equals("ja")) {
                        return "Japanese";
                    }
                    break;
                case 3427:
                    if (lowerCase.equals("kn")) {
                        return "Kannada";
                    }
                    break;
                case 3428:
                    if (lowerCase.equals("ko")) {
                        return "Korean";
                    }
                    break;
                case 3487:
                    if (lowerCase.equals("ml")) {
                        return "Malayalam";
                    }
                    break;
                case 3588:
                    if (lowerCase.equals("pt")) {
                        return "Portuguese";
                    }
                    break;
                case 3651:
                    if (lowerCase.equals("ru")) {
                        return "Russian";
                    }
                    break;
                case 3693:
                    if (lowerCase.equals("ta")) {
                        return "Tamil";
                    }
                    break;
                case 3697:
                    if (lowerCase.equals("te")) {
                        return "Telugu";
                    }
                    break;
                case 3886:
                    if (lowerCase.equals("zh")) {
                        return "Chinese";
                    }
                    break;
            }
        }
        if (code == null) {
            return null;
        }
        String upperCase = code.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
        return upperCase;
    }

    public static /* synthetic */ Object invokeSubtitleAPI$default(SubUtils subUtils, String str, Integer num, Integer num2, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        return subUtils.invokeSubtitleAPI(str, num, num2, function1, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x02de -> B:55:0x0304). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSubtitleAPI(@org.jetbrains.annotations.Nullable java.lang.String r27, @org.jetbrains.annotations.Nullable java.lang.Integer r28, @org.jetbrains.annotations.Nullable java.lang.Integer r29, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r30, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r31) {
        /*
            Method dump skipped, instruction units count: 858
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hdo.SubUtils.invokeSubtitleAPI(java.lang.String, java.lang.Integer, java.lang.Integer, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object invokeWyZIESUBAPI$default(SubUtils subUtils, String str, Integer num, Integer num2, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            num2 = null;
        }
        return subUtils.invokeWyZIESUBAPI(str, num, num2, function1, continuation);
    }

    /* JADX WARN: Path cross not found for [B:101:0x0235, B:54:0x024a], limit reached: 96 */
    /* JADX WARN: Removed duplicated region for block: B:24:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x03e6  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:89:0x039c -> B:90:0x03bf). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeWyZIESUBAPI(@org.jetbrains.annotations.Nullable java.lang.String r31, @org.jetbrains.annotations.Nullable java.lang.Integer r32, @org.jetbrains.annotations.Nullable java.lang.Integer r33, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r34, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instruction units count: 1016
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hdo.SubUtils.invokeWyZIESUBAPI(java.lang.String, java.lang.Integer, java.lang.Integer, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
