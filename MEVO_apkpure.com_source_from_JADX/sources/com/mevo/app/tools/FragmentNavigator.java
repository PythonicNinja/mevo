package com.mevo.app.tools;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.mevo.app.presentation.main.MainFragment;
import java.util.concurrent.ConcurrentLinkedQueue;

public class FragmentNavigator {
    private AppCompatActivity activity;
    private ViewGroup fragmentContainer;
    private ConcurrentLinkedQueue<FragmentOperation> operationsQueue = new ConcurrentLinkedQueue();

    public interface FragmentOperation {
        void executeOperation(FragmentManager fragmentManager, ViewGroup viewGroup);
    }

    private class PopBackstackFragmentOperation implements FragmentOperation {
        private PopBackstackFragmentOperation() {
        }

        public void executeOperation(FragmentManager fragmentManager, ViewGroup viewGroup) {
            FragmentNavigator.this.activity.getSupportFragmentManager().popBackStack();
        }
    }

    private class RefreshFragmentTransaction implements FragmentOperation {
        private RefreshFragmentTransaction() {
        }

        public void executeOperation(FragmentManager fragmentManager, ViewGroup viewGroup) {
            viewGroup = fragmentManager.findFragmentById(viewGroup.getId());
            fragmentManager.beginTransaction().detach(viewGroup).commit();
            fragmentManager.beginTransaction().attach(viewGroup).commitAllowingStateLoss();
        }
    }

    private class ReplaceFragmentOperation implements FragmentOperation {
        private boolean addToBackStack;
        private MainFragment fragment;

        public ReplaceFragmentOperation(MainFragment mainFragment, boolean z) {
            this.fragment = mainFragment;
            this.addToBackStack = z;
        }

        public void executeOperation(FragmentManager fragmentManager, ViewGroup viewGroup) {
            fragmentManager = FragmentNavigator.this.activity.getSupportFragmentManager().findFragmentById(viewGroup.getId());
            if (fragmentManager == null || this.fragment.getClass().equals(fragmentManager.getClass()) == null) {
                fragmentManager = FragmentNavigator.this.activity.getSupportFragmentManager().beginTransaction().replace(viewGroup.getId(), this.fragment);
                if (this.addToBackStack != null) {
                    fragmentManager.addToBackStack(null);
                }
                fragmentManager.commit();
            }
        }
    }

    public void onPostResume(AppCompatActivity appCompatActivity, ViewGroup viewGroup) {
        this.activity = appCompatActivity;
        this.fragmentContainer = viewGroup;
        executePendingTransactions();
    }

    public void onPause() {
        this.activity = null;
        this.fragmentContainer = null;
    }

    public void changeFragment(@NonNull MainFragment mainFragment, boolean z) {
        addAndTryExecute(new ReplaceFragmentOperation(mainFragment, z));
    }

    public void popBackstack() {
        addAndTryExecute(new PopBackstackFragmentOperation());
    }

    public void refreshCurrentFragment() {
        addAndTryExecute(new RefreshFragmentTransaction());
    }

    private void executePendingTransactions() {
        if (this.operationsQueue.peek() != null) {
            if (this.activity != null) {
                while (!this.operationsQueue.isEmpty()) {
                    ((FragmentOperation) this.operationsQueue.poll()).executeOperation(this.activity.getSupportFragmentManager(), this.fragmentContainer);
                }
            }
        }
    }

    private void addAndTryExecute(FragmentOperation fragmentOperation) {
        this.operationsQueue.add(fragmentOperation);
        executePendingTransactions();
    }

    public void showFirstFragmentIfShould(MainFragment mainFragment, FragmentManager fragmentManager, ViewGroup viewGroup) {
        if (viewGroup != null && viewGroup.getChildCount() == null) {
            changeFragment(mainFragment, null);
        }
    }
}
