package com.mevo.app.tools;

import android.text.TextUtils;
import com.mevo.app.data.model.reportable_error.ProblemNode;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ProblemsListParser {
    private static final String TAG = "ProblemsListParser";

    public static List<ProblemNode> parseErrorsListJson(String str, boolean z) {
        List<ProblemNode> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            str = jSONObject.keys();
            while (str.hasNext()) {
                String str2 = (String) str.next();
                ProblemNode parseNode = parseNode(jSONObject.optJSONObject(str2), str2, null);
                if (parseNode != null) {
                    arrayList.add(parseNode);
                }
            }
        } catch (String str3) {
            Log.ex(TAG, str3);
        }
        if (z) {
            str3 = arrayList.size() - 1;
            while (str3 >= null) {
                if (!(((ProblemNode) arrayList.get(str3)).code.equals(ProblemNode.CATEGORY_BIKE) || ((ProblemNode) arrayList.get(str3)).code.equals(ProblemNode.CATEGORY_STATION))) {
                    arrayList.remove(str3);
                }
                str3--;
            }
        }
        return arrayList;
    }

    private static ProblemNode parseNode(JSONObject jSONObject, String str, ProblemNode problemNode) {
        if (jSONObject == null) {
            return null;
        }
        ProblemNode problemNode2 = new ProblemNode(problemNode);
        if (problemNode == null) {
            problemNode2.setName(jSONObject.optString("name"));
            problemNode2.type = 1;
        } else if (TextUtils.isDigitsOnly(str) != null) {
            problemNode2.setName(str);
            problemNode2.type = 2;
        } else if ("more".equals(str) != null) {
            problemNode2.setName("...");
            problemNode2.type = 3;
        } else {
            problemNode2.setName(jSONObject.optString("name"));
            problemNode2.type = 3;
        }
        problemNode2.code = str;
        if (problemNode2.getName() == null) {
            return null;
        }
        str = jSONObject.keys();
        while (str.hasNext() != null) {
            String str2 = (String) str.next();
            JSONObject optJSONObject = jSONObject.optJSONObject(str2);
            if (optJSONObject != null) {
                problemNode = parseNode(optJSONObject, str2, problemNode2);
            } else {
                problemNode = parseLeafNode(str2, jSONObject.optString(str2), problemNode2);
            }
            if (problemNode != null) {
                problemNode2.children.add(problemNode);
            }
        }
        if (problemNode2.children.size() > null) {
            return problemNode2;
        }
        return null;
    }

    private static ProblemNode parseLeafNode(String str, String str2, ProblemNode problemNode) {
        if (!(str == null || str.isEmpty() || str.equals("name") || str2 == null)) {
            if (!str2.isEmpty()) {
                ProblemNode problemNode2 = new ProblemNode(problemNode);
                problemNode2.type = 4;
                problemNode2.setName(str2);
                problemNode2.code = str;
                return problemNode2;
            }
        }
        return null;
    }
}
