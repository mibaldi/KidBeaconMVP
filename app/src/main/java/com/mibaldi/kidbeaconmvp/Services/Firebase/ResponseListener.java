package com.mibaldi.kidbeaconmvp.services.firebase;

/**
 * Created by mikelbalducieldiaz on 25/9/16.
 */

public interface ResponseListener {
    public abstract void onSuccess(String s);
    public abstract void onError(int code,String description);
    public abstract void onProgress(long total,long progress);
}
