package com.audacity.ridemate.Model.LocalModel;

import com.audacity.ridemate.Model.ClientData;
import com.audacity.ridemate.Model.DataSource;

import java.util.Collection;
import java.util.List;

/**
 * Created by Prince on 6/5/17.
 */

public interface ILocalDataSource<T> extends DataSource<List<ClientData>> {
    boolean doesDataExist();
    void save(T clientDataList);
}
