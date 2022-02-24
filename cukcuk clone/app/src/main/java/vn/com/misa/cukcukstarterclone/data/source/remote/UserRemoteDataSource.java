package vn.com.misa.cukcukstarterclone.data.source.remote;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import vn.com.misa.cukcukstarterclone.data.IOnLoadedCallback;
import vn.com.misa.cukcukstarterclone.data.source.IUserDataSource;

/**
 * @created_by KhanhNQ on 08-Feb-2021.
 */
public class UserRemoteDataSource implements IUserDataSource.Remote {

    private static final String TAG = "";

    private final FirebaseAuth mAuth;

    private UserRemoteDataSource(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    private static UserRemoteDataSource instance;

    public static UserRemoteDataSource getInstance(FirebaseAuth firebaseAuth) {
        if (instance == null) {
            instance = new UserRemoteDataSource(firebaseAuth);
        }
        return instance;
    }

    @Override
    public void login(String username, String password, IOnLoadedCallback<Boolean> callback) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        callback.onSuccess(true);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        callback.onFailure(task.getException());
                    }
                });
    }

    @Override
    public void register(String username, String password, IOnLoadedCallback<Boolean> callback) {
        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        callback.onSuccess(true);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        callback.onFailure(task.getException());
                    }
                });
    }
}
