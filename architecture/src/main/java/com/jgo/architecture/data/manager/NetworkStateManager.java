

package com.jgo.architecture.data.manager;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.jgo.architecture.bridge.callback.UnPeekLiveData;

/**
 * Create by jgo at 19/10/11
 */
public class NetworkStateManager implements DefaultLifecycleObserver {

    private static NetworkStateManager sManager = new NetworkStateManager();

    private NetworkStateManager() {
    }

    public static NetworkStateManager getInstance() {
        return sManager;
    }

    public final UnPeekLiveData<NetState> mNetworkStateCallback = new UnPeekLiveData<>();
    private NetworkStateReceive mNetworkStateReceive;


    @Override
    public void onStart(@NonNull LifecycleOwner owner) {
        /*mNetworkStateReceive = new NetworkStateReceive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).registerReceiver(mNetworkStateReceive, filter);
        } else if (owner instanceof Fragment) {
            ((AppCompatActivity) Objects.requireNonNull(((Fragment) owner).getActivity()))
                    .registerReceiver(mNetworkStateReceive, filter);
        }*/
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        /*if (owner instanceof AppCompatActivity) {
            ((AppCompatActivity) owner).unregisterReceiver(mNetworkStateReceive);
        } else if (owner instanceof Fragment) {
            ((AppCompatActivity) Objects.requireNonNull(((Fragment) owner).getActivity()))
                    .unregisterReceiver(mNetworkStateReceive);
        }*/
    }
}
