package pkgfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

/**
 * @author Frank Izquierdo
 * @author Misaykel Molina
 */
public class Final extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("VeganDishFinal.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
       public static void main(String[] args)
   {
      // Create a named constant for the URL.
      // NOTE: This value is specific for Java DB.
      final String DB_URL = "jdbc:derby://localhost:1527/vegan";
      
      try
      {
         // Create a connection to the database.
         Connection conn =
                DriverManager.getConnection(DB_URL);
					 
			// If the DB already exists, drop the tables.
			//dropTables(conn);
			
			// Build the Recipe table.
			buildRecipeTable(conn);
			
			// Build the Ingredients table.
			buildIngredientsTable(conn);

			// Build the Customer table.
			buildRecipeIngredientTable(conn);
			
			// Build the Image table.
			buildImageTable(conn);

			

         // Close the connection.
         conn.close();
         
         //test connection
         testConnection();
         
         launch(args);
      }
      catch (Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
      
      launch(args);
   }
	
	/**
	 * The dropTables method drops any existing
	 * in case the database already exists.
	 */
	public static void dropTables(Connection conn)
	{
		System.out.println("Checking for existing tables.");
		
		try
		{
			// Get a Statement object.
			Statement stmt  = conn.createStatement();

			try
			{
	         // Drop the Recipe table.
                            stmt.execute("DROP TABLE Recipe");
                            System.out.println("Recipe table dropped.");
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
			}

			try
			{
                                // Drop the Ingredients table.
                            stmt.execute("DROP TABLE Ingredients");
                            System.out.println("Ingredients table dropped.");				
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
			}

			try
			{
                            // Drop the Recipe_Ingredients table.
                            stmt.execute("DROP TABLE Recipe_Ingredients");
                            System.out.println("Recipe_Ingredients table dropped.");
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
                        }

			try
			{
                            // Drop the Image table.
                            stmt.execute("DROP TABLE Image");
                            System.out.println("Image table dropped.");
			}
			catch(SQLException ex)
			{
				// No need to report an error.
				// The table simply did not exist.
			}



		}
  		catch(SQLException ex)
		{
                    System.out.println("ERROR: " + ex.getMessage());
                    ex.printStackTrace();
		}
	}
	
	/**
	 * The buildIngredientsTable method creates the
	 * Recipe table and adds some rows to it.
	 */
	public static void buildIngredientsTable(Connection conn)
	{
		try
		{
         // Get a Statement object.
         Statement stmt = conn.createStatement();
         
                       	// Create the table.
			//stmt.execute("CREATE TABLE Ingredients(ing_ID int NOT NULL PRIMARY KEY, ing_name varchar(30), ing_price varchar(50), ing_source clob(4000), ing_environment clob(4000), ing_health clob(4000) )");

			stmt.execute("INSERT INTO Ingredients VALUES (1, 'Creamy peanut butter','$3.99/16oz', 'Grown in 3 major areas of the U.S.: The Southeast, Southwest, and Virginia & North Carolina', '1kg of peanuts produces 2.88kg of CO2 emissions', 'Good protein source high in healthy fats rich in antioxidants vitamins and minerals')");

			stmt.execute("INSERT INTO Ingredients VALUES (2, 'Soy sauce','$2.99/10oz','The United States is the largest producer of Soybeans and Brazil is second. The top US states growing soybeans are Illinois and Iowa.', 'The soybean industry is causing widespread deforestation and displacement of small farmers and indigenous peoples around the globe.', 'Promotes digestion and gut health, antioxidants')");

			stmt.execute("INSERT INTO Ingredients VALUES(3,'Apple cider vinegar','$4.99/16oz','The U.S. is the second largest apple producer behind China. The main areas where apples are grown are Washington New York; Michigan Pennsylvania California Virginia North Carolina Oregon Ohio and Idaho.', 'Apples and other tree fruit crops inherently have a minimal environmental footprint says David Granatstein WSU sustainable agriculture specialist.  To grow apples you could simply plant a seed water it from a bucket as necessary remove weeds by hand', 'Lowers blood sugar levels lowers cholesterol possibly helps prevent cancer')");

			stmt.execute("INSERT INTO Ingredients VALUES(4,'Maple syrup','$7.99/12oz','The largest producer of maple syrup is Quebec Canada and the second is Vermont USA.','Processing 800 gallons of maple sap takes approximately 60 gallons of oil or a cord of wood to produce 20 gallons of finished syrup. These costs for using non-renewable energy sources such as oil and the resulting pollution are very high. The Colorado Dep','Better than regular sugar but still use in moderation')");

			stmt.execute("INSERT INTO Ingredients VALUES(5, 'Grated ginger', '$5.99/1lb', 'India is the largest producer of ginger in the world, followed by China and Nepal. The majority of ginger in the US is imported, or grown in Hawaii.', 'What the farmers are ignoring is the harmful effect of ginger cultivation on the environment. Ginger is prone to rhizome rot viral disease. Since farmers do not want to risk crop failure, they use large quantities of chemicalsï¿½pesticides, herbicides, fung', 'Gingerol, the bioactive substance in fresh ginger, can help lower the risk of infections. It also lowers cholesterol and blood sugar.')");

                        stmt.execute("INSERT INTO Ingredients VALUES(6,'Garlic','$5.99/1lb','The majority of U.S. production is concentrated in California Globally, China is the largest producer of garlic (? rds of the worldï¿½s total)and is the dominant','Importation of garlic from China or California contributes to carbon emissions because of the transportation by boat, plane, or truck.','Highly nutritious, few calories, combats sickness, reduce blood pressure, contains antioxidants')");

			stmt.execute("INSERT INTO Ingredients VALUES(7, 'Red pepper flakes', '$2.69/0.99oz', 'Red pepper flakes are made up from a variety of different peppers. U.S. producers source their peppers from South America, China, and Turkey.', 'Carbon emissions from the transport of red peppers from around the world.', 'Red chili peppers, such as cayenne, have been shown to reduce blood cholesterol, triglyceride levels, and platelet aggregation while increasing the body ability to dissolve fibrin, a substance integral to the formation of blood clots.')");

			stmt.execute("INSERT INTO Ingredients VALUES(8,'Water','A couple of cents from the tap', '90% of tap water in Miami and Florida comes from groundwater sources. Miami-Dade County gets its drinking water from the local Biscayne Aquifer.', 'Currently, water is readily available in Miami. In other parts of the world, fresh clean water is hard to come by. As climate change continues the availability of fresh water will drop.', 'Water is essential to life and drinking plenty of water throughout the day is vital for peopleï¿½s health.')");

			stmt.execute("INSERT INTO Ingredients VALUES(9,'Sweet potatoes','$1.99/1lb','North Carolina is the leading Sweet potato producing state','When grown using traditional methods, both sweetpotato and yam are widely considered to be environmentally friendly, relative to cereal crops. They are easily inter-cropped and do not require a complete clearing of forest for planting, and are thus amenab','Sweet potatoes are a great source of fiber, vitamins, and minerals. Lots of Vitamin A. The fiber and antioxidants in sweet potatoes are advantageous to gut health.')");

			stmt.execute("INSERT INTO Ingredients VALUES(10,'Red bell pepper','$3.99/1lb','California produces 47% of US bell peppers. Immokalee Florida also produces bell peppers, with a history of human rights violations','Bell pepper production involves the use of pesticides, which can be especially harmful to natural water sources as the toxins in the pesticides runoff from farms into rivers and lakes.','Bell peppers are loaded with various vitamins and minerals, and are exceptionally rich in vitamin C.')");

			stmt.execute("INSERT INTO Ingredients VALUES(11,'Coconut oil','$5.99/14oz','Coconut oil mostly produced in the Philippines, Indonesia, and India.','In general, the environmental impact of coconut oil is very low. However, as demand for coconut oil and coconut water has skyrocketed, coastal mangroves, which are essential ecosystems for animals and provide natural storm protection, are being cleared fo','This superfood is loaded with saturated fats that actually raise HDL (good) cholesterol and lower your risk of heart disease.')");

			stmt.execute("INSERT INTO Ingredients VALUES(12,'Cumin powder','$2.99/1.55lbs','Cumin is cultivated for commercial use in Morocco, Turkey, Greece, Egypt, Iran and India.','Transportation of cumin from foreign countries produces a lot of carbon emissions.','increases antioxidant intake, promotes digestion, provides iron, may improve blood sugar control and may reduce food-borne illnesses.')");

			stmt.execute("INSERT INTO Ingredients VALUES(13,'Sea salt','$1.69/26.5oz','Sea salt is produced through evaporation of ocean water or water from saltwater lakes. The only sea salt production in the U.S. occurs in San Francisco.','Environmentally, sea salt collection can be a low-impact operation. Unfortunately, some companies manage to find ways to increase the impact by trucking loads long distances or speeding up the evaporation process by using fossil fuel-based energy.','Helps you stay hydrated, promotes good vascular health, balances electrolytes, supports a healthy nervous system, improves sleep.')");

			stmt.execute("INSERT INTO Ingredients VALUES(14,'Jasmine brown rice','$5.49/32oz','90% grown in Asia','Rice requires double or triple the amount of water as the other cereal crops to produce the same dry weight. ï¿½rice fields may produce as much methane as all the worldï¿½s natural wetlands combinedï¿½','According to HSPH, the fiber in brown rice helps lower cholesterol, moves waste through the digestive tract, promotes fullness, and may help prevent the formation of blood clots. Brown rice is considered a low glycemic index food. The glycemic index (GI)')");

			stmt.execute("INSERT INTO Ingredients VALUES(15,'Green onions','$0.99/1 bunch','California is a major producer of green onions in the US. Arizona, Georgia, Idaho, New Mexico, Oregon, Texas, and Washington are other states with sizable commercial green onion production. If not grown in the US, green onions are often imported from Mexi','It is common for crops to be sprayed with pesticides, but to combat this environmental issue you can buy organic green onions which are grown without pesticides.','Low calorie, full of vitamin A and K.')");

                        stmt.execute("INSERT INTO Ingredients VALUES(16,'Cilantro','$1.99/1 bunch','Cilantro is mostly grown in California.','Cilantro can be easily grown in home gardens, reducing the environmental impacts from transporting the crop from out of state.','Leaves full of antioxidants, essential oils, vitamins (specifically A and K), and dietary fiber.')");

                        stmt.execute("INSERT INTO Ingredients VALUES(17,'Peanuts','$3.49/16oz','major areas of the U.S.: The Southeast, Southwest, and Virginia & North Carolina','1kg of peanuts produces 2.88kg of CO2 emissions','Good protein source, high in healthy fats, rich in antioxidants, vitamins, and minerals')");

                        stmt.execute("INSERT INTO Ingredients VALUES(18,'Sriracha','$4.99/16oz','Huy Fong Foods sriracha sauce is produced in Irwindale, California.','Sriracha is sold in plastic containers which are not environmentally friendly.','Boosts metabolism, aids in weight loss, strengthen the immune system, lowers blood pressure and cholesterol, helps regulate blood sugar levels, helps dissolve blood clots, fights colds.')");
							 
			System.out.println("Ingredients table created.");
		}
		catch (SQLException ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
	}


	/**
	 * The buildRecipe Table method creates the
	 * Coffee table and adds some rows to it.
	 */
	public static void buildRecipeTable(Connection conn)
	{
		try
		{
         // Get a Statement object.
         Statement stmt = conn.createStatement();
         
			// Create the table.
			//stmt.execute("CREATE TABLE Recipe(rec_ID int NOT NULL PRIMARY KEY, rec_name varchar(100) NOT NULL, rec_time varchar(100), rec_desc clob(4000), rec_serving int, rec_mealType varchar(50), rec_cookType varchar(100), rec_prep varchar(255), rec_procedure clob(4000))");
							 
			// Insert row #1.
			stmt.execute("INSERT INTO Recipe VALUES (1," + 
                            "'Spicy Thai Peanut Sauce over Roasted Sweet Potatoes and Rice'," +
                            "'Prep: 10 min, Cook Time: 40 min, Total time: 50 min'," +
                            "'Bold and spicy Thai peanut sauce drizzled over roasted sweet potatoes and bell peppers on a bed of rice. The sauce would also be great on stir-fries and salads, or as a dip for raw vegetables. This is a healthy vegetarian, vegan and gluten-free recipe.'," +
                            "4,'Dinner or lunch'," +
                            "'Rice is cooked on the stove top, the vegetables are roasted in the oven'," +
                            "'Prep: Bring a large pot of water to boil. Preheat the oven to 425 degrees Fahrenheit',"+ // with a rack in the middle and another rack near the top.'," +
                            "'Roast the vegetables: On a large, rimmed baking sheet, toss the sweet potato with a generous tablespoon of coconut oil, the cumin and a sprinkle of salt. Arrange them in a single layer, and set aside.  " +  
                            "On a separate, smaller baking sheet, toss the bell pepper with about 1 teaspoon of coconut oil and a sprinkle of salt. Toss until lightly coated, and arrange them in a single layer. " +
                            "Roast the sweet potatoes on the middle rack for about 35 minutes, tossing halfway, and roast the peppers on the top rack for about 20 minutes, tossing halfway. The vegetables will be tender and caramelized on the edges when they are ready.  " +
                            "In the meantime, cook the rice: Once the water is boiling, pour in the rice and give it a stir. Boil the rice for 30 minutes, then turn off the heat and drain the rice. Return the rice to the pot and cover the pot. Let the rice steam that way for 10 minutes. Remove the lid, fluff the rice with a fork and season with salt to taste.  " +
                            "Make the sauce: In a bowl, whisk together the sauce ingredients. If the sauce is too thick or too spicy, whisk in a little more water.  " +
                            "Serve: In bowls, divide the rice and roasted vegetables. Drizzle each bowl generously with sauce, and top with a sprinkle of green onions, cilantro and peanuts.')");

							 
			System.out.println("Recipe table created.");
		}
		catch (SQLException ex)
                {
                    System.out.println("ERROR: " + ex.getMessage());
                }
	}





	/**
	 * The buildRecipe_IngredientTable method creates the
	 * Customer table and adds some rows to it.
	 */
	public static void buildRecipeIngredientTable(Connection conn)
	{
      try
      {
         // Get a Statement object.
         Statement stmt = conn.createStatement();
         
         // Create the table.
		//stmt.execute("CREATE TABLE Recipe_Ingredient(rec_id int NOT NULL REFERENCES Recipe(rec_ID), ing_id int NOT NULL REFERENCES Ingredients(ing_ID), recIng_measurement varchar(255))");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,1,'1 cup')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,2,'1 cup')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,3,'3 tablespoons')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,4,'2 tablespoons')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,5,'1 teaspoon')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,6,'2 cloves')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,7,'1 teaspoon')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,8,'2 tablespoons')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,9,'2')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,10,'1')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,11,'2 tablespoons')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,12,'1 teaspoon')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,13,'to taste')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,14,'1 cup')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,15,'2-3')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,16,'handful')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,17,'handful')");
		stmt.execute("INSERT INTO Recipe_Ingredients VALUES (1,18,'optional on the side')");
					
			System.out.println("Recipe_Ingredient table created.");
		}
		catch (SQLException ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
	}

	/**
	 * The buildImageTable method creates
	 * the Image table.
	 */

	public static void buildImageTable(Connection conn)
	{
      try
      {
         // Get a Statement object.
         Statement stmt = conn.createStatement();
			
         // Create the table.
         stmt.execute("CREATE TABLE Image(Ima_ID int NOT NULL PRIMARY KEY, rec_id int NOT NULL REFERENCES Recipe(rec_ID), ima_path varchar(255), ima_name varchar(255))");
				
			System.out.println("Image table created.");
		}
		catch (SQLException ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
	}
        
        
        public static void testConnection()
        {
             final String DB_URL = "jdbc:derby://localhost:1527/veganTest";
      
      try
      {
         // Create a connection to the database.
         Connection conn = DriverManager.getConnection(DB_URL);
         System.out.println("Connection created.");
         
  // Create a Statement object.
         Statement stmt = conn.createStatement();
         
         // Create a string with a SELECT statement.
         String sqlStatement = 
            //"SELECT * FROM recipe";
            "SELECT * FROM Recipe_Ingredients";
         // Send the statement to the DBMS.
         ResultSet result = stmt.executeQuery(sqlStatement);
         
         // Display a header for the listing.
         System.out.println("Vegan Dishes costing more than $10.00");
         System.out.println("--------------------------------");
         
         // Display the contents of the result set.
         // The result set will have three columns.
         while (result.next())
         {
            System.out.println(result.getInt("ing_id") + 
                               result.getString("rec_id") + 
                               result.getString("recing_measurment"));
         
         
         
         }                
         
         
         
         // Close the connection.
         conn.close();
         System.out.println("Connection closed.");
      }
      catch(Exception ex)
      {
         System.out.println("ERROR: " + ex.getMessage());
      }
        }
}
    
  
