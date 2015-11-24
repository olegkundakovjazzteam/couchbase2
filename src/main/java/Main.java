import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

/**
 * Created by OlegKundakovJazzteam on 24.11.2015.
 */
public class Main {
    public static void main(String[] a) {
        Cluster cluster = CouchbaseCluster.create();

// Open the default bucket and the "beer-sample" one
        Bucket defaultBucket = cluster.openBucket();
//        Bucket beerSampleBucket = cluster.openBucket("beer-sample");
    }
}
