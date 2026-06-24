package com.cncverse;

import com.lagradost.cloudstream3.SubtitleFile;
import com.lagradost.cloudstream3.utils.ExtractorApi;
import com.lagradost.cloudstream3.utils.ExtractorLink;
import com.lagradost.cloudstream3.utils.Qualities;
import java.net.URI;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: Extractors.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u000f0\u00132\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000f0\u0013H\u0096@¢\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0002R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/cncverse/HubCloud;", "Lcom/lagradost/cloudstream3/utils/ExtractorApi;", "<init>", "()V", "name", "", "getName", "()Ljava/lang/String;", "mainUrl", "getMainUrl", "requiresReferer", "", "getRequiresReferer", "()Z", "getUrl", "", "url", "referer", "subtitleCallback", "Lkotlin/Function1;", "Lcom/lagradost/cloudstream3/SubtitleFile;", "callback", "Lcom/lagradost/cloudstream3/utils/ExtractorLink;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIndexQuality", "", "str", "getBaseUrl", "cleanTitle", "title", "MLSBDProvider_debug"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nExtractors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Extractors.kt\ncom/cncverse/HubCloud\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,606:1\n1#2:607\n1#2:626\n296#3,2:608\n1915#3,2:610\n1642#3,10:612\n1915#3:622\n1807#3,3:623\n1916#3:627\n1652#3:628\n*S KotlinDebug\n*F\n+ 1 Extractors.kt\ncom/cncverse/HubCloud\n*L\n348#1:626\n132#1:608,2\n163#1:610,2\n348#1:612,10\n348#1:622\n354#1:623,3\n348#1:627\n348#1:628\n*E\n"})
public class HubCloud extends ExtractorApi {
    private final boolean requiresReferer;

    @NotNull
    private final String name = "Hub-Cloud";

    @NotNull
    private final String mainUrl = "https://hubcloud.foo";

    /* JADX INFO: renamed from: com.cncverse.HubCloud$getUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: Extractors.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.HubCloud", f = "Extractors.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12}, l = {129, 151, 172, 182, 191, 197, 215, 225, 235, 245, 255, 264, 301}, m = "getUrl$suspendImpl", n = {"$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "$this$getUrl_u24lambda_u242", "$i$a$-runCatching-HubCloud$getUrl$href$1", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "resp", "dlink", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "base", "finalUrl", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2", "$this", "url", "referer", "subtitleCallback", "callback", "tag", "ref", "uri", "realUrl", "baseUrl", "href", "document", "size", "header", "headerDetails", "labelExtras", "$this$forEach$iv", "element$iv", "element", "link", "text", "label", "quality", "$i$f$forEach", "$i$a$-forEach-HubCloud$getUrl$2"}, nl = {131, 152, 171, 181, 192, 196, 214, 224, 234, 244, 256, 263, 304}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "L$24", "L$25", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "L$24", "L$25", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$15", "L$16", "L$18", "L$19", "L$20", "L$21", "L$22", "I$0", "I$1", "I$2"}, v = 2)
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
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$20;
        Object L$21;
        Object L$22;
        Object L$23;
        Object L$24;
        Object L$25;
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
            return HubCloud.getUrl$suspendImpl(HubCloud.this, null, null, null, null, (Continuation) this);
        }
    }

    @Nullable
    public Object getUrl(@NotNull String str, @Nullable String str2, @NotNull Function1<? super SubtitleFile, Unit> function1, @NotNull Function1<? super ExtractorLink, Unit> function12, @NotNull Continuation<? super Unit> continuation) {
        return getUrl$suspendImpl(this, str, str2, function1, function12, continuation);
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public String getMainUrl() {
        return this.mainUrl;
    }

    public boolean getRequiresReferer() {
        return this.requiresReferer;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:(1:333)|246|247|325|248|249|(1:251)(3:252|253|259)) */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x0b2b, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:254:0x1667, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:255:0x1668, code lost:
    
        r5 = r21;
        r8 = r13;
        r13 = r27;
        r6 = r8;
        r32 = r67;
        r30 = r2;
        r1 = r4;
        r8 = 0;
        r2 = r14;
        r4 = r16;
        r26 = r20;
        r25 = r34;
        r34 = r35;
        r28 = r38;
        r29 = r39;
        r27 = r40;
        r14 = r60;
        r11 = r0;
        r10 = r7;
        r7 = r66;
        r20 = r18;
        r35 = r23;
        r15 = r36;
        r23 = r22;
        r22 = r19;
        r19 = r9;
        r9 = r3;
        r3 = r12;
        r12 = r59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:343:0x1acd, code lost:
    
        r6 = r2;
        r2 = r21;
        r8 = r15;
        r41 = r5;
        r21 = r9;
        r31 = r13;
        r27 = r20;
        r29 = r38;
        r11 = r39;
        r5 = r1;
        r1 = r14;
        r20 = r18;
        r18 = r17;
        r17 = r16;
        r16 = r12;
        r12 = r40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0a90, code lost:
    
        r0 = (org.jsoup.nodes.Element) r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0a94, code lost:
    
        if (r0 == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0a97, code lost:
    
        r0 = r0.attr(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0aa4, code lost:
    
        r0 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0aa5, code lost:
    
        if (r0 != null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0aa7, code lost:
    
        r0 = r24;
     */
    /* JADX WARN: Path cross not found for [B:307:0x09f4, B:67:0x0a0e], limit reached: 334 */
    /* JADX WARN: Path cross not found for [B:67:0x0a0e, B:307:0x09f4], limit reached: 334 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0ab4  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0ac0 A[Catch: all -> 0x0b2b, TryCatch #8 {all -> 0x0b2b, blocks: (B:99:0x0aab, B:102:0x0ac0, B:107:0x0ace, B:89:0x0a90), top: B:315:0x0a90 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0bc3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0bef  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0bf2  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0c7b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0c81  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0c84  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0c8e  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0c93  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0c96  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0cb3  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0cb6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0cbe  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0cdd  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0ceb  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0cef  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0d0e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0d49  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x10a5  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x10b7  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x10c5  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x11a6  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x1ae5  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x09f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0a21 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0a82 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ java.lang.Object getUrl$suspendImpl(com.cncverse.HubCloud r63, java.lang.String r64, java.lang.String r65, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.SubtitleFile, kotlin.Unit> r66, kotlin.jvm.functions.Function1<? super com.lagradost.cloudstream3.utils.ExtractorLink, kotlin.Unit> r67, kotlin.coroutines.Continuation<? super kotlin.Unit> r68) {
        /*
            Method dump skipped, instruction units count: 6960
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.HubCloud.getUrl$suspendImpl(com.cncverse.HubCloud, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final int getIndexQuality(String str) {
        List groupValues;
        String str2;
        Integer intOrNull;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("(\\d{3,4})[pP]"), str == null ? "" : str, 0, 2, (Object) null);
        return (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str2 = (String) CollectionsKt.getOrNull(groupValues, 1)) == null || (intOrNull = StringsKt.toIntOrNull(str2)) == null) ? Qualities.P2160.getValue() : intOrNull.intValue();
    }

    private final String getBaseUrl(String url) {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            HubCloud hubCloud = this;
            URI it = new URI(url);
            obj = Result.constructor-impl(it.getScheme() + "://" + it.getHost());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = "";
        }
        return (String) obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01d1 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.lang.String cleanTitle(java.lang.String r33) {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.HubCloud.cleanTitle(java.lang.String):java.lang.String");
    }
}
