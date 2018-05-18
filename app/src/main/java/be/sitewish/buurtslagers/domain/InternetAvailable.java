package be.sitewish.buurtslagers.domain;

import android.os.AsyncTask;

public class InternetAvailable extends AsyncTask<Void, Void, Boolean > {

    public AsyncResponse delgate = null;

    public InternetAvailable(AsyncResponse delgate) {
        this.delgate = delgate;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return Controller.InternetAvailable();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        delgate.processFinish(aBoolean ? 1 : 0);
    }
}