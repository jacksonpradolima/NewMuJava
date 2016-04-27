/**
 * Copyright (C) 2015  the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * <p>
 * Description: </p>
 *
 * @author Jeff Offutt and Yu-Seung Ma
 * @version 1.0
 */
package mujava.op.util;

import java.io.*;
import mujava.MutationSystem;

import org.json.simple.JSONObject;

public class TraditionalMutantCodeWriter extends MutantCodeWriter {

    String method_signature = null;

    public TraditionalMutantCodeWriter(PrintWriter out) {
        super(out);
    }

    public TraditionalMutantCodeWriter(String mutant_dir, PrintWriter out) {
        super(mutant_dir, out);
    }

    public void setMethodSignature(String str) {
        method_signature = str;
    }

    @Override
    protected void writeLog(String changed_content) {
        /*
        JSONObject obj = new JSONObject();
        obj.put("MutationOperator", class_name.split("_")[0]);
        obj.put("Name", class_name);
        obj.put("LineNumber", mutated_line);
        obj.put("MutatedMethod", method_signature);
        obj.put("Mutation", changed_content);
        obj.put("Class", MutationSystem.CLASS_NAME + ".java");
        obj.put("MutationPath", MutationSystem.MUTANT_PATH + "/" + method_signature + "/" + class_name + "/" + MutationSystem.CLASS_NAME + ".java");
        CodeChangeLog.writeLog(obj);
         */
        CodeChangeLog.writeLog(class_name + MutationSystem.LOG_IDENTIFIER
                               + mutated_line + MutationSystem.LOG_IDENTIFIER
                               + method_signature + MutationSystem.LOG_IDENTIFIER
                               + changed_content);
    }
}
