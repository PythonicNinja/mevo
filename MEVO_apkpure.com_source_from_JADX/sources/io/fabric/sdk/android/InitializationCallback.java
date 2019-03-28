package io.fabric.sdk.android;

public interface InitializationCallback<T> {
    public static final InitializationCallback EMPTY = new Empty();

    public static class Empty implements InitializationCallback<Object> {
        public void failure(Exception exception) {
        }

        public void success(Object obj) {
        }

        private Empty() {
        }
    }

    void failure(Exception exception);

    void success(T t);
}
