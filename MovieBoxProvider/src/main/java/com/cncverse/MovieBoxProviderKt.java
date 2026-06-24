package com.cncverse;

import com.lagradost.cloudstream3.utils.Qualities;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

/* JADX INFO: compiled from: MovieBoxProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0015\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001a:\u0010\u0007\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u0006\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0082@¢\u0006\u0002\u0010\r\u001a:\u0010\u000e\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u0006\u0010\u000f\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0082@¢\u0006\u0002\u0010\r\u001a\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0002\u001a\"\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@¢\u0006\u0002\u0010\u001a\u001a<\u0010\u001b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u00012\b\u0010\u001f\u001a\u0004\u0018\u00010\u0003H\u0086@¢\u0006\u0002\u0010 ¨\u0006!"}, d2 = {"getHighestQuality", "", "input", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "cleanTitle", "s", "identifyID", "Lkotlin/Pair;", "title", "year", "imdbRatingValue", "", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Double;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchAndPick", "normTitle", "tokenEquals", "", "a", "b", "normalize", "fetchMetaData", "Lcom/fasterxml/jackson/databind/JsonNode;", "imdbId", "type", "Lcom/lagradost/cloudstream3/TvType;", "(Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchTmdbLogoUrl", "tmdbAPI", "apiKey", "tmdbId", "appLangCode", "(Ljava/lang/String;Ljava/lang/String;Lcom/lagradost/cloudstream3/TvType;Ljava/lang/Integer;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MovieBoxProvider_debug"}, k = 2, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMovieBoxProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MovieBoxProvider.kt\ncom/cncverse/MovieBoxProviderKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1384:1\n777#2:1385\n873#2,2:1386\n777#2:1388\n873#2,2:1389\n777#2:1391\n873#2,2:1392\n1#3:1394\n*S KotlinDebug\n*F\n+ 1 MovieBoxProvider.kt\ncom/cncverse/MovieBoxProviderKt\n*L\n1217#1:1385\n1217#1:1386,2\n1276#1:1388\n1276#1:1389,2\n1277#1:1391\n1277#1:1392,2\n*E\n"})
public final class MovieBoxProviderKt {

    /* JADX INFO: renamed from: com.cncverse.MovieBoxProviderKt$fetchMetaData$1, reason: invalid class name */
    /* JADX INFO: compiled from: MovieBoxProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieBoxProviderKt", f = "MovieBoxProvider.kt", i = {0, 0, 0, 0}, l = {1302}, m = "fetchMetaData", n = {"imdbId", "type", "metaType", "url"}, nl = {1303}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieBoxProviderKt.fetchMetaData(null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieBoxProviderKt$fetchTmdbLogoUrl$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieBoxProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieBoxProviderKt", f = "MovieBoxProvider.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1324}, m = "fetchTmdbLogoUrl", n = {"tmdbAPI", "apiKey", "type", "tmdbId", "appLangCode", "url", "$i$a$-runCatching-MovieBoxProviderKt$fetchTmdbLogoUrl$json$1"}, nl = {1324}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"}, v = 2)
    static final class C00031 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        C00031(Continuation<? super C00031> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieBoxProviderKt.fetchTmdbLogoUrl(null, null, null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieBoxProviderKt$identifyID$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieBoxProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieBoxProviderKt", f = "MovieBoxProvider.kt", i = {0, 0, 0, 0}, l = {1163}, m = "identifyID", n = {"title", "year", "imdbRatingValue", "normTitle"}, nl = {1164}, s = {"L$0", "L$1", "L$2", "L$3"}, v = 2)
    static final class C00041 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        C00041(Continuation<? super C00041> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieBoxProviderKt.identifyID(null, null, null, (Continuation) this);
        }
    }

    /* JADX INFO: renamed from: com.cncverse.MovieBoxProviderKt$searchAndPick$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: MovieBoxProvider.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @DebugMetadata(c = "com.cncverse.MovieBoxProviderKt", f = "MovieBoxProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {1187, 1190, 1191, 1268}, m = "searchAndPick", n = {"normTitle", "year", "imdbRatingValue", "normTitle", "year", "imdbRatingValue", "multiResults", "normTitle", "year", "imdbRatingValue", "multiResults", "normTitle", "year", "imdbRatingValue", "multiResults", "searchQueues", "bestId", "detailKind", "detailUrl", "bestScore", "bestIsTv"}, nl = {1189, 1189, 1189, 1269}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "D$0", "I$0"}, v = 2)
    static final class C00051 extends ContinuationImpl {
        double D$0;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        C00051(Continuation<? super C00051> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MovieBoxProviderKt.searchAndPick(null, null, null, (Continuation) this);
        }
    }

    @Nullable
    public static final Integer getHighestQuality(@NotNull String input) {
        List<Pair> qualities = CollectionsKt.listOf(new Pair[]{TuplesKt.to("2160", Integer.valueOf(Qualities.P2160.getValue())), TuplesKt.to("1440", Integer.valueOf(Qualities.P1440.getValue())), TuplesKt.to("1080", Integer.valueOf(Qualities.P1080.getValue())), TuplesKt.to("720", Integer.valueOf(Qualities.P720.getValue())), TuplesKt.to("480", Integer.valueOf(Qualities.P480.getValue())), TuplesKt.to("360", Integer.valueOf(Qualities.P360.getValue())), TuplesKt.to("240", Integer.valueOf(Qualities.P240.getValue()))});
        for (Pair pair : qualities) {
            String label = (String) pair.component1();
            int mappedValue = ((Number) pair.component2()).intValue();
            if (StringsKt.contains(input, label, true)) {
                return Integer.valueOf(mappedValue);
            }
        }
        return null;
    }

    private static final String cleanTitle(String s) {
        String lowerCase = s.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trim(new Regex("\\s+").replace(new Regex("[^a-z0-9 ]").replace(lowerCase, " "), " ")).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object identifyID(java.lang.String r6, java.lang.Integer r7, java.lang.Double r8, kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.Integer, java.lang.String>> r9) throws org.json.JSONException {
        /*
            boolean r0 = r9 instanceof com.cncverse.MovieBoxProviderKt.C00041
            if (r0 == 0) goto L14
            r0 = r9
            com.cncverse.MovieBoxProviderKt$identifyID$1 r0 = (com.cncverse.MovieBoxProviderKt.C00041) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            com.cncverse.MovieBoxProviderKt$identifyID$1 r0 = new com.cncverse.MovieBoxProviderKt$identifyID$1
            r0.<init>(r9)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L44;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L2c:
            java.lang.Object r2 = r0.L$3
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r0.L$2
            r8 = r3
            java.lang.Double r8 = (java.lang.Double) r8
            java.lang.Object r3 = r0.L$1
            r7 = r3
            java.lang.Integer r7 = (java.lang.Integer) r7
            java.lang.Object r3 = r0.L$0
            r6 = r3
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r1
            goto L6e
        L44:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.String r3 = normalize(r6)
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r6)
            r0.L$0 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
            r0.L$1 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r8)
            r0.L$2 = r4
            java.lang.Object r4 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r3)
            r0.L$3 = r4
            r4 = 1
            r0.label = r4
            java.lang.Object r4 = searchAndPick(r3, r7, r8, r0)
            if (r4 != r2) goto L6d
            return r2
        L6d:
            r2 = r3
        L6e:
            r3 = r4
            kotlin.Pair r3 = (kotlin.Pair) r3
            java.lang.Object r4 = r3.getFirst()
            if (r4 == 0) goto L78
            return r3
        L78:
            kotlin.Pair r4 = new kotlin.Pair
            r5 = 0
            r4.<init>(r5, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieBoxProviderKt.identifyID(java.lang.String, java.lang.Integer, java.lang.Double, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x01a0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0201 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r24v3 */
    /* JADX WARN: Type inference failed for: r24v4 */
    /* JADX WARN: Type inference failed for: r25v1 */
    /* JADX WARN: Type inference failed for: r25v2 */
    /* JADX WARN: Type inference failed for: r25v3 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [int] */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v46 */
    /* JADX WARN: Type inference failed for: r4v47 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object searchAndPick(java.lang.String r46, java.lang.Integer r47, java.lang.Double r48, kotlin.coroutines.Continuation<? super kotlin.Pair<java.lang.Integer, java.lang.String>> r49) throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 1342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieBoxProviderKt.searchAndPick(java.lang.String, java.lang.Integer, java.lang.Double, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object searchAndPick$doSearch(java.lang.String r21, java.lang.String r22, kotlin.coroutines.Continuation<? super org.json.JSONArray> r23) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieBoxProviderKt.searchAndPick$doSearch(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object searchAndPick$doSearch$default(String str, String str2, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        return searchAndPick$doSearch(str, str2, continuation);
    }

    private static final boolean tokenEquals(String a, String b) {
        Iterable $this$filter$iv = new Regex("\\s+").split(a, 0);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            String it = (String) element$iv$iv;
            if (!StringsKt.isBlank(it)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Set sa = CollectionsKt.toSet((List) destination$iv$iv);
        Iterable $this$filter$iv2 = new Regex("\\s+").split(b, 0);
        Collection destination$iv$iv2 = new ArrayList();
        for (Object element$iv$iv2 : $this$filter$iv2) {
            String it2 = (String) element$iv$iv2;
            if (!StringsKt.isBlank(it2)) {
                destination$iv$iv2.add(element$iv$iv2);
            }
        }
        Set sb = CollectionsKt.toSet((List) destination$iv$iv2);
        if (sa.isEmpty() || sb.isEmpty()) {
            return false;
        }
        int inter = CollectionsKt.intersect(sa, sb).size();
        return inter >= Math.max(1, (Math.min(sa.size(), sb.size()) * 3) / 4);
    }

    private static final String normalize(String s) {
        String lowerCase = StringsKt.trim(new Regex("(?i)\\b(dub|dubbed|hd|4k|hindi|tamil|telugu|dual audio)\\b").replace(new Regex("\\(.*?\\)").replace(new Regex("\\[.*?]").replace(s, " "), " "), " ")).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String t = new Regex("\\s+").replace(new Regex("\\p{Punct}").replace(StringsKt.replace$default(lowerCase, ":", " ", false, 4, (Object) null), " "), " ");
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object fetchMetaData(java.lang.String r23, com.lagradost.cloudstream3.TvType r24, kotlin.coroutines.Continuation<? super com.fasterxml.jackson.databind.JsonNode> r25) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieBoxProviderKt.fetchMetaData(java.lang.String, com.lagradost.cloudstream3.TvType, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x015c A[ADDED_TO_REGION, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object fetchTmdbLogoUrl(@org.jetbrains.annotations.NotNull java.lang.String r24, @org.jetbrains.annotations.NotNull java.lang.String r25, @org.jetbrains.annotations.NotNull com.lagradost.cloudstream3.TvType r26, @org.jetbrains.annotations.Nullable java.lang.Integer r27, @org.jetbrains.annotations.Nullable java.lang.String r28, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r29) {
        /*
            Method dump skipped, instruction units count: 576
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cncverse.MovieBoxProviderKt.fetchTmdbLogoUrl(java.lang.String, java.lang.String, com.lagradost.cloudstream3.TvType, java.lang.Integer, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final String fetchTmdbLogoUrl$path(JSONObject o) {
        return o.optString("file_path");
    }

    private static final boolean fetchTmdbLogoUrl$isSvg(JSONObject o) {
        return StringsKt.endsWith(fetchTmdbLogoUrl$path(o), ".svg", true);
    }

    private static final String fetchTmdbLogoUrl$urlOf(JSONObject o) {
        return "https://image.tmdb.org/t/p/w500" + fetchTmdbLogoUrl$path(o);
    }

    private static final boolean fetchTmdbLogoUrl$voted(JSONObject o) {
        return o.optDouble("vote_average", 0.0d) > 0.0d && o.optInt("vote_count", 0) > 0;
    }

    private static final boolean fetchTmdbLogoUrl$better(JSONObject a, JSONObject b) {
        if (a == null) {
            return true;
        }
        double aAvg = a.optDouble("vote_average", 0.0d);
        int aCnt = a.optInt("vote_count", 0);
        double bAvg = b.optDouble("vote_average", 0.0d);
        int bCnt = b.optInt("vote_count", 0);
        if (bAvg <= aAvg) {
            return ((bAvg > aAvg ? 1 : (bAvg == aAvg ? 0 : -1)) == 0) && bCnt > aCnt;
        }
        return true;
    }
}
