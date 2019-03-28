package com.mevo.app.presentation.adapters;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.mevo.app.data.model.reportable_error.ProblemNode;
import com.mevo.app.presentation.custom_views.CheckButton;
import com.mevo.app.tools.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class ProblemsAdapter extends BaseAdapter implements OnClickListener {
    private static final String TAG = "ProblemsAdapter";
    private ProblemNode currentChecked;
    private List<ProblemNode> currentItems = new ArrayList();
    private String currentProblemCategory;
    private ProblemNode currentRoot;
    private List<ProblemNode> originalData = new ArrayList();
    private ProblemCategoryListener problemCategoryListener;

    public interface ProblemCategoryListener {
        void onProblemCategoryChanged(String str);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ProblemsAdapter(List<ProblemNode> list, ProblemCategoryListener problemCategoryListener) {
        setOriginalData(list);
        this.problemCategoryListener = problemCategoryListener;
    }

    public void destroy() {
        for (ProblemNode destroy : this.originalData) {
            destroy.destroy();
        }
    }

    public void setProblemCategoryListener(ProblemCategoryListener problemCategoryListener) {
        this.problemCategoryListener = problemCategoryListener;
    }

    public void setOriginalData(List<ProblemNode> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.originalData = list;
        setData(this.originalData);
    }

    private void setData(List<ProblemNode> list) {
        if (list == null) {
            list = new ArrayList();
        }
        this.currentItems = new ArrayList(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.currentItems.size();
    }

    public ProblemNode getItem(int i) {
        return (ProblemNode) this.currentItems.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        i = getItem(i);
        if (view == null) {
            view = new CheckButton(viewGroup.getContext());
        } else {
            view = (CheckButton) view;
        }
        view.setButtonText(i.getName());
        boolean z = false;
        view.setCheckVisible(i.type == 4 ? true : null);
        if (i == this.currentChecked) {
            z = true;
        }
        view.setChecked(z);
        view.setOnClickListener(this);
        view.setTag(i);
        return view;
    }

    public void onClick(View view) {
        ProblemNode problemNode = (ProblemNode) view.getTag();
        if (problemNode.type == 4) {
            changeLeafNodeState(problemNode);
        } else {
            goForward(problemNode);
        }
    }

    private void changeLeafNodeState(ProblemNode problemNode) {
        if (this.currentChecked != null) {
            if (this.currentChecked == problemNode) {
                this.currentChecked = null;
                notifyDataSetChanged();
            }
        }
        this.currentChecked = problemNode;
        notifyDataSetChanged();
    }

    public boolean canGoBack() {
        return this.currentRoot != null;
    }

    private void goTo(ProblemNode problemNode) {
        if (problemNode == null) {
            setData(this.originalData);
        } else {
            setData(problemNode.children);
        }
        this.currentRoot = problemNode;
        this.currentChecked = null;
        setCurrentMainCategory();
    }

    private void setCurrentMainCategory() {
        CharSequence charSequence = this.currentProblemCategory;
        this.currentProblemCategory = null;
        if (this.currentRoot != null) {
            ProblemNode problemNode = this.currentRoot;
            while (problemNode.parent != null) {
                problemNode = problemNode.parent;
                if (problemNode.type == 1) {
                    this.currentProblemCategory = problemNode.code;
                }
            }
        }
        if (!TextUtils.equals(charSequence, this.currentProblemCategory) && this.problemCategoryListener != null) {
            this.problemCategoryListener.onProblemCategoryChanged(this.currentProblemCategory);
        }
    }

    public void goBack() {
        if (!canGoBack()) {
            return;
        }
        if (this.currentRoot.type == 2) {
            goTo(this.currentRoot.parent.parent);
        } else {
            goTo(this.currentRoot.parent);
        }
    }

    public void goForward(ProblemNode problemNode) {
        if (problemNode.children.isEmpty() || ((ProblemNode) problemNode.children.get(0)).type != 2) {
            goTo(problemNode);
        } else {
            goTo((ProblemNode) problemNode.children.get(0));
        }
    }

    public JSONObject getProblemChain() {
        JSONObject jSONObject = new JSONObject();
        List arrayList = new ArrayList();
        ProblemNode problemNode = this.currentChecked;
        if (problemNode == null) {
            problemNode = this.currentRoot;
        }
        if (problemNode == null) {
            return jSONObject;
        }
        while (problemNode != null && problemNode.type != 1) {
            arrayList.add(problemNode);
            problemNode = problemNode.parent;
        }
        Collections.reverse(arrayList);
        int i = 0;
        JSONObject jSONObject2 = jSONObject;
        while (i < arrayList.size()) {
            try {
                ProblemNode problemNode2 = (ProblemNode) arrayList.get(i);
                if (problemNode2.type == 2) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject2.put(problemNode2.code, jSONObject3);
                    jSONObject2 = jSONObject3;
                } else if (problemNode2.type == 3 || problemNode2.type == 4) {
                    jSONObject2.put(Integer.toString(getSizeForIterator(jSONObject2.keys()) + 1), problemNode2.code);
                }
                i++;
            } catch (Throwable e) {
                Log.ex(TAG, e);
            }
        }
        return jSONObject;
    }

    private int getSizeForIterator(Iterator it) {
        int i = 0;
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i;
    }

    public String getProblemCategory() {
        return this.currentProblemCategory;
    }
}
