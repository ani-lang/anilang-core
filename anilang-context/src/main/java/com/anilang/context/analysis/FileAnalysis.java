/*
 * Property of Opencore
 */

package com.anilang.context.analysis;

/**
 * Run an analysis over an Ani file.
 * Use {@link FileAnalysisBuilder} to set up the analysis.
 *
 * @since 0.7.1
 */
interface FileAnalysis {

    /**
     * Run the analysis.
     * {@link DctrArgs#context()} contains all the analysis.
     *
     * @return Args used and populated in the analysis.
     */
    DctrArgs run();
}
