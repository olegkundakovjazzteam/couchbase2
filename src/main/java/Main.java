import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;


/**
 * Created by OlegKundakovJazzteam on 24.11.2015.
 */
public class Main {
    public static void main(String[] a) throws InterruptedException {

        Cluster cluster = CouchbaseCluster.create();
        Bucket bucket = cluster.openBucket();

//        JsonObject user = JsonObject.empty()
//                .put("type", "car")
//                .put("name", "jo")
//                .put("age", 2)
//                .put("color", "red");

        bucket
                .async()
                .get("walter")
                .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
                    @Override
                    public Observable<JsonDocument> call(final JsonDocument loaded) {
                        loaded.content().put("age", 52);
                        return bucket.async().replace(loaded);
                    }
                })
                .subscribe(new Action1<JsonDocument>() {
                    @Override
                    public void call(final JsonDocument updated) {
                        System.out.println("Updated: " + updated.id());
                    }
                });
        Thread.sleep(1000);

        cluster.disconnect();


    }
}