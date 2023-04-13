/*
 * Property of ani-lang project.
 */

package com.anilang.context.utils;

import com.anilang.context.AniContext;
import com.anilang.context.ContextMetadata;
import com.anilang.context.impl.PositionKey;
import java.util.function.Consumer;
import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Fetch metadata from the context.
 *
 * @since 0.7.1
 */
public final class MetadataFrom {

    /**
     * Context.
     */
    private final AniContext context;

    /**
     * Key.
     */
    private final String key;

    /**
     * Ctor.
     *
     * @param context Context.
     * @param rule Rule.
     */
    public MetadataFrom(final AniContext context, final ParserRuleContext rule) {
        this(context, new PositionKey(rule).toString());
    }

    /**
     * Ctor.
     *
     * @param context Context.
     * @param key Key.
     */
    public MetadataFrom(final AniContext context, final String key) {
        this.context = context;
        this.key = key;
    }

    /**
     * Metadata if found in the context.
     *
     * @param consumer Accept the metadata if found.
     */
    public void ifPresent(final Consumer<ContextMetadata> consumer) {
        if (this.context.contains(this.key)) {
            consumer.accept(this.context.get(this.key));
        }
    }
}
