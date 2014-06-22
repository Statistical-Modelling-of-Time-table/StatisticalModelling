package edu.iiitb.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.schema.IndexDefinition;
import org.neo4j.graphdb.schema.Schema;

public class CreateDatabase extends ActionSupport 
{
	int train,station;
	
	public int getTrain() {
		return train;
	}

	public void setTrain(int train) {
		this.train = train;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	private static enum RelTypes implements RelationshipType
    {
        CONNECTED,
        STARTSFROM
    }
	
	private static final String DB_PATH = "C:/Users/Venkatesh/Documents/Neo4j/neo4j_train_station.db";
	
	public String execute()
	{
		System.out.println("No of trains are : "+train);
		System.out.println("No of ststion are : "+station);
    
		System.out.println( "Starting database ..." );
		deleteFileOrDirectory( new File( DB_PATH ) );

		// START SNIPPET: startDb
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( DB_PATH );
		// END SNIPPET: startDb

		{
		     // START SNIPPET: createIndex
		     IndexDefinition indexDefinition1, indexDefinition2;
		            try ( Transaction tx = graphDb.beginTx() )
		            {
		                Schema schema = graphDb.schema();
		                indexDefinition1 = schema.indexFor( DynamicLabel.label( "Train" ) )
		                        .on( "Train_details" )
		                        .create();
		                indexDefinition2 = schema.indexFor( DynamicLabel.label( "Station" ) )
		                        .on( "Station_details" )
		                        .create();
		                tx.success();
		            }
		            // END SNIPPET: createIndex
		            // START SNIPPET: wait
		            try ( Transaction tx = graphDb.beginTx() )
		            {
		                Schema schema = graphDb.schema();
		                schema.awaitIndexOnline( indexDefinition1, 10, TimeUnit.SECONDS );
		                schema.awaitIndexOnline( indexDefinition2, 10, TimeUnit.SECONDS );
		            }
		            // END SNIPPET: wait
		   }

		   {
		            // START SNIPPET: addUsers
		            try ( Transaction tx1 = graphDb.beginTx() )
		            {
		                Label label = DynamicLabel.label( "Train" );

		                // Create some users
		                for ( int id = 0; id < train; id++ )
		                {
		                    Node TrainNode = graphDb.createNode( label );
		                    TrainNode.setProperty( "Train_details", "Train" + id);
		                }
		                System.out.println( "Train created" );
		                
		                label = DynamicLabel.label( "Station" );
		                // Create some users
		                for ( int id = 0; id < station; id++ )
		                {
		                    Node StationNode = graphDb.createNode( label );
		                    StationNode.setProperty( "Station_details", "Station" + id);
		                }
		                System.out.println( "Stations created" );
		              
		                for(int id=1;id<99;id++)
		                {
			                label = DynamicLabel.label( "Station" );
			                int idToFind = id;
			                String nameToFind = "Station" + idToFind;
			                ResourceIterator<Node> node1 = graphDb.findNodesByLabelAndProperty( label, "Station_details", nameToFind ).iterator();
			                int idToFind1 = id+1;
			                String nameToFind1 = "Station" + idToFind1;
			                ResourceIterator<Node> node2 = graphDb.findNodesByLabelAndProperty( label, "Station_details", nameToFind1 ).iterator();
			                Node firstUserNode=null;
			                if ( node1.hasNext() )
			                {
			                    firstUserNode = node1.next();
			                }
			                Node secondUserNode=null;
			                if ( node2.hasNext() )
			                {
			                    secondUserNode = node2.next();
			                }
			                Relationship relationship1 = (firstUserNode).createRelationshipTo(secondUserNode, RelTypes.CONNECTED );
			                relationship1.setProperty( "message", "Is Connected " );
			                tx1.success();
		                }
		                for(int id=1;id<99;id++)
		                {
			                label = DynamicLabel.label( "Station" );
			                Label l1 = DynamicLabel.label("Train");
			                int idToFind = id;
			                String nameToFind = "Station" + idToFind;
			                ResourceIterator<Node> node1 = graphDb.findNodesByLabelAndProperty( label, "Station_details", nameToFind ).iterator();
			                String nameToFind1 = "Train" + idToFind;
			                ResourceIterator<Node> node2 = graphDb.findNodesByLabelAndProperty( l1, "Train_details", nameToFind1 ).iterator();
			                Node firstUserNode=null;
			                if ( node1.hasNext() )
			                {
			                    firstUserNode = node1.next();
			                }
			                Node secondUserNode=null;
			                if ( node2.hasNext() )
			                {
			                    secondUserNode = node2.next();
			                }
			                System.out.println(firstUserNode);
			                System.out.println(secondUserNode);
			                Relationship relationship = (secondUserNode).createRelationshipTo(firstUserNode, RelTypes.STARTSFROM );
			                relationship.setProperty( "message", "Is starting from " );
		                }

		            }
			        catch(Exception e)
			        {
			            System.out.println(e);
			        }
 
		    }

		    {
		            // START SNIPPET: dropIndex
		            try ( Transaction tx4 = graphDb.beginTx() )
		            {
		                Label label = DynamicLabel.label( "Train" );
		                for ( IndexDefinition indexDefinition : graphDb.schema()
		                        .getIndexes( label ) )
		                {
		                    // There is only one index
		                    indexDefinition.drop();
		                }

		                tx4.success();
		            }
		            // END SNIPPET: dropIndex
		      }

		        System.out.println( "Shutting down database ..." );
		        // START SNIPPET: shutdownDb
		        graphDb.shutdown();
		        // END SNIPPET: shutdownDb
		        
		        return "success";
		}

		    private static void deleteFileOrDirectory( File file )
		    {
		        if ( file.exists() )
		        {
		            if ( file.isDirectory() )
		            {
		                for ( File child : file.listFiles() )
		                {
		                    deleteFileOrDirectory( child );
		                }
		            }
		            file.delete();
		        }
		    }
}
		    
