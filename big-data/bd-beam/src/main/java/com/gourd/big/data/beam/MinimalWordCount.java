/*
 *
 * The MIT License
 * Copyright © 2018-2018 GourdErwa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.gourd.big.data.beam;

import java.util.Arrays;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Count;
import org.apache.beam.sdk.transforms.Filter;
import org.apache.beam.sdk.transforms.FlatMapElements;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;

/**
 * The type Minimal word count.
 *
 * @author wei.Li by 2018-12-13
 */
public final class MinimalWordCount {

    private MinimalWordCount() {
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);

        // Concept #1: Apply a root transform to the pipeline; in this case, TextIO.Read to read a set
        // of input text files. TextIO.Read returns a PCollection where each element is one line from
        // the input text (a set of Shakespeare's texts).

        // This example reads a public data set consisting of the complete works of Shakespeare.
        p.apply(TextIO.read().from("gs://apache-beam-samples/shakespeare/*"))

            // Concept #2: Apply a FlatMapElements transform the PCollection of text lines.
            // This transform splits the lines in PCollection<String>, where each element is an
            // individual word in Shakespeare's collected texts.
            .apply(
                FlatMapElements.into(TypeDescriptors.strings())
                    .via((String word) -> Arrays.asList(word.split("[^\\p{L}]+"))))
            // We use a Filter transform to avoid empty word
            .apply(Filter.by((String word) -> !word.isEmpty()))
            // Concept #3: Apply the Count transform to our PCollection of individual words. The Count
            // transform returns a new PCollection of key/value pairs, where each key represents a
            // unique word in the text. The associated value is the occurrence count for that word.
            .apply(Count.perElement())
            // Apply a MapElements transform that formats our PCollection of word counts into a
            // printable string, suitable for writing to an output file.
            .apply(
                MapElements.into(TypeDescriptors.strings())
                    .via(
                        (KV<String, Long> wordCount) ->
                            wordCount.getKey() + ": " + wordCount.getValue()))
            // Concept #4: Apply a write transform, TextIO.Write, at the end of the pipeline.
            // TextIO.Write writes the contents of a PCollection (in this case, our PCollection of
            // formatted strings) to a series of text files.
            // By default, it will write to a set of files with names like wordcounts-00001-of-00005
            .apply(TextIO.write().to("wordcounts"));

        p.run().waitUntilFinish();
    }
}
