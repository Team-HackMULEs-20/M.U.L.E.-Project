package gameConfig;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.naming.ldap.Control;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

	@FXML
	private Button startButton;

	@FXML
	private Button landBuyButton;

	@FXML
	private Button passButton;

	@FXML
	private Button townButton;

	@FXML
	private Button landOfficeButton;

	@FXML
	private Button pubButton;

	@FXML
	private TextField bidAmount;

	@FXML
	public static Label currentBidLabel;

	@FXML
	private Button bidButton;

	@FXML
	private Button bidPassButton;

	@FXML
	private Button gambleButton;

	@FXML
	private Button backButton;

	@FXML
	private Button storeButton;

	@FXML
	private Button gambleOkButton;

	@FXML
	private Button passSelect;

	@FXML
	private Button selectLand;

	@FXML
	private Label foodCostLabel;

	@FXML
	private Label energyCostLabel;

	@FXML
	private Label smithoreCostLabel;

	@FXML
	private Label chrystiteCostLabel;

	@FXML
	private Label muleCostLabel;

	@FXML
	private Label foodQuantityLabel;

	@FXML
	private Label energyQuantityLabel;

	@FXML
	private Label smithoreQuantityLabel;

	@FXML
	private Label chrystiteQuantityLabel;

	@FXML
	private ComboBox muleChoice;

	//    @FXML
	//    private Label buyFoodButton;
	//
	//    @FXML
	//    private Label muleQuantityLabel;
	//
	//    @FXML
	//    private Label muleQuantityLabel;

	public static Mule.Type currentMuleType;
	public static Store store;


	public static int numPasses = 0;
	private static Stage start;
	private Stage newStage;
	private static boolean selectPhase = true;

//	private Stage infoStage;
//	private Label moneyLeft = new Label();
//	private Label foodLeft = new Label();
//	private Label currRound = new Label();
//	private Label energyLeft = new Label();
//	private Label oreLeft = new Label();
//	private Label currPlayer = new Label();
//	private boolean infoBarCreated = false;
    private boolean infoBarCreated = false;
    InfoBar infoBar;
	private Auction auction = new Auction();

	// TURNS AND SETUP
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		assert startButton != null : "fx:id=\"startButton\" was not injected: " +
				"check your FXML file 'playerStart.fxml'.";
		assert landBuyButton != null : "fx:id=\"landBuyButton\" was not injected: " +
				"check your FXML file 'landBuyInterface.fxml'.";
		assert passButton != null : "fx:id=\"passButton\" was not injected: " +
				"check your FXML file 'landBuyInterface.fxml'.";
		assert townButton != null : "fx:id=\"townButton\" was not injected: " +
				"check your FXML file 'MainMap.fxml'.";
		assert landOfficeButton != null : "fx:id=\"landOfficeButton\" was not injected: " +
				"check your FXML file 'TownMap.fxml'.";
	}

	@FXML
	public static void beginTurn() {
		Launcher.primaryStage.hide();
		start = new Stage();
		start.setScene(Launcher.startScene);
		start.setTitle(Turns.getTurn().getName() + "'s Turn");
		start.show();
	}

	public void startButtonClicked(ActionEvent event) {
		newStage = new Stage();
        if (!infoBarCreated){
            infoBar = new InfoBar();
            infoBarCreated = true;
        } else {
            infoBar.updateInfoBar();
        }
		//infoBar();
        //infoBar.updateInfoBar();
		if (event.getSource() == startButton) {
			Timer timer = new Timer(Turns.timeForTurn(Turns.getTurn()));
			timer.start();
			start.close();

			store = new Store();
			/* boolean auctionTime = auction.isAuctionTime();
			System.out.println("before auction if statement");
			if (auctionTime) {
				System.out.println("Auction time");
				auction.startAuction();
			} */
			if (numPasses < Controller.numPlayer) {
				System.out.println("Land Selection Phase");
				selectPhase = true;
				newStage.setScene(Launcher.selectLandPhase);
				newStage.setTitle(Turns.getTurn().getName());
				newStage.show();
			} else {
				selectPhase = false;
			}
		}
	}

//	public void infoBar() {
//		infoStage = new Stage();
//		Player p = Turns.getTurn();
//		if (!infoBarCreated) {
//			infoBarCreated = true;
//			infoStage.setTitle("Information Bar");
//			infoStage.setAlwaysOnTop(true);
//			Group t2 = new Group();
//			Scene scene2 = new Scene(t2, 600, 100);
//
//			currRound.setText("Round " + Turns.rounds);
//			currRound.setFont(new Font("American Typewriter Bold", 18));
//
//			currPlayer.setText("It is " + p.getName() + "'s Turn");
//			currPlayer.setFont(new Font("American Typewriter Bold", 18));
//			if (p.getColor() != Color.WHITE) {
//				currPlayer.setTextFill(p.getColor());
//			} else {
//				currPlayer.setTextFill(p.getColor());
//				currPlayer.setBackground(new Background(new BackgroundFill(Color.BLACK,
//                        CornerRadii.EMPTY, Insets.EMPTY)));
//			}
//
//
//			moneyLeft.setText("Money: " + p.getMoney());
//			moneyLeft.setFont(new Font("American Typewriter", 15));
//
//			foodLeft.setText("Food: " + p.getFood());
//			foodLeft.setFont(new Font("American Typewriter", 15));
//
//			energyLeft.setText("Energy: " + p.getEnergy());
//			energyLeft.setFont(new Font("American Typewriter", 15));
//
//			oreLeft.setText("Ore: " + p.getOre());
//			oreLeft.setFont(new Font("American Typewriter", 15));
//
//			Label l1 = new Label("                                          ");
//			Label l2 = new Label("                                          ");
//			Label l3 = new Label("                                          ");
//
//			GridPane grid2 = new GridPane();
//			grid2.setAlignment(Pos.CENTER);
//			grid2.setHgap(20);
//			grid2.setVgap(10);
//			grid2.add(l1, 1, 0);
//			grid2.add(currPlayer, 2, 0);
//			grid2.add(currRound, 4, 0);
//			grid2.add(l2, 1, 1);
//			grid2.add(moneyLeft, 2, 1);
//			grid2.add(foodLeft, 4, 1);
//			grid2.add(l3, 1, 2);
//			grid2.add(energyLeft, 2, 2);
//			grid2.add(oreLeft, 4, 2);
//			t2.getChildren().add(grid2);
//
//			infoStage.setScene(scene2);
//			infoStage.show();
//			infoStage.toFront();
//
//            System.out.println("INFO BAR CREATED");
//
//		} else {
//			currRound.setText("Round " + Turns.rounds);
//			currRound.setFont(new Font("American Typewriter Bold", 18));
//
//			currPlayer.setText("It is " + p.getName() + "'s Turn");
//			currPlayer.setFont(new Font("American Typewriter Bold", 18));
//			if (p.getColor() != Color.WHITE) {
//				currPlayer.setTextFill(p.getColor());
//				currPlayer.setBackground(new Background(new BackgroundFill(Color.WHITE,
//						CornerRadii.EMPTY, Insets.EMPTY)));
//			} else {
//				currPlayer.setTextFill(p.getColor());
//				currPlayer.setBackground(new Background(new BackgroundFill(Color.BLACK,
//                        CornerRadii.EMPTY, Insets.EMPTY)));
//			}
//
//			moneyLeft.setText("Money: " + p.getMoney());
//            System.out.println("in info bar "+ p.getName() + " " + p.getMoney());
//			moneyLeft.setFont(new Font("American Typewriter", 15));
//
//			foodLeft.setText("Food: " + p.getFood());
//			foodLeft.setFont(new Font("American Typewriter", 15));
//
//			energyLeft.setText("Energy: " + p.getEnergy());
//			energyLeft.setFont(new Font("American Typewriter", 15));
//
//			oreLeft.setText("Ore: " + p.getOre());
//			oreLeft.setFont(new Font("American Typewriter", 15));
//            System.out.println("INFO BAR UPDATED");
//		}
//
//		if (Turns.rounds + 1 == 14) {
//			infoStage.close();
//		}
//	}

	@FXML
	public void bidButtonClicked(ActionEvent event) {
		if (event.getSource() == bidButton) {
			int currentBidAmount = Integer.parseInt(bidAmount.getText());
			Player p = Turns.getTurn();
			auction.placeBid(p, currentBidAmount);
		} else if (event.getSource() == bidPassButton) {
			Timer.endTurn();
			auction.auctionStage.close();
		}
	}

	@FXML
	public void selectionPhase(ActionEvent event) {
		if (event.getSource() == selectLand) {
			if (Turns.getTurn().getLandGrants() > 0 || Turns.getTurn().getMoney() > 300)//make sure player can buy land
				Land.landBuyEnable = true;
			Stage stage = (Stage) selectLand.getScene().getWindow();
			stage.close();
		} else {
			numPasses++;
			Stage stage = (Stage) passSelect.getScene().getWindow();
			stage.close();
			Timer.endTurn();
		}
	}

	//TOWN BUTTONS
	public void townButtonClicked(ActionEvent e) {
		newStage = new Stage();
		if (e.getSource() == townButton) {
			Launcher.primaryStage.hide();
			newStage.setScene(Launcher.townScene);
			newStage.setTitle("Town");
			newStage.show();
			Player p = Turns.getTurn();
		}
	}

	@FXML
	public void backButtonClicked(ActionEvent e) {
		newStage = new Stage();
		if (e.getSource() == backButton) {
			newStage.setScene(Launcher.townScene);
			newStage.setTitle("Town");
			newStage.show();
		}
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	//LAND OFFICE
	public void landOfficeButtonClicked(ActionEvent e) {
		newStage = new Stage();
		if (e.getSource() == landOfficeButton) {
			newStage.setScene(Launcher.landBuyIntScene);
			newStage.setTitle(Turns.getTurn().getName());
			newStage.show();
		}
		Stage stage = (Stage) landOfficeButton.getScene().getWindow();
		stage.close();
	}

	public void buyLandButtonClicked(ActionEvent e) {
		if (e.getSource() == landBuyButton) {
			if (Turns.getTurn().getLandGrants() > 0 || Turns.getTurn().getMoney() > 300)//make sure player can buy land
				Land.landBuyEnable = true;
			Stage stage = (Stage) landBuyButton.getScene().getWindow();
			stage.close();
		} else if (e.getSource() == passButton) {
			Stage stage = (Stage) passButton.getScene().getWindow();
			stage.close();
		}
	}

	public void landButtonClicked(ActionEvent e) {
		Player currentP = Turns.getTurn();
		Node landButton = (Node) e.getSource();
		GridPane grid = (GridPane) landButton.getParent();
		int col = GridPane.getColumnIndex(landButton);
		int row = GridPane.getRowIndex(landButton);
		Land newLand = new Land(GridPane.getColumnIndex(landButton), GridPane.getRowIndex(landButton));
		if (Land.landBuyEnable) {
			if (currentP.landGrants > 0) {//check for land grants
				currentP.landGrants--;

				Rectangle color =  new Rectangle();
				color.setFill(currentP.getColor());
				color.setHeight(25);
				color.setWidth(25);
				color.setOpacity(1);
				grid.add(color, col, row);
				GridPane.setHalignment(color, HPos.LEFT);
				GridPane.setValignment(color, VPos.TOP);
				// landButton.setDisable(true);//disable the land button since land is purchased

				Controller.landPlots[col][row].setOwner(currentP);
				currentP.landOwned.add(newLand);

			} else if (currentP.getMoney() >= 300){//if not grants sub money
				currentP.addSubMoney(-300);

				Rectangle color =  new Rectangle();
				color.setFill(currentP.getColor());
				color.setHeight(25);
				color.setWidth(25);
				color.setOpacity(1);
				grid.add(color, col, row);
				GridPane.setHalignment(color, HPos.LEFT);
				GridPane.setValignment(color, VPos.TOP);
				// landButton.setDisable(true);//disable the land button since land is purchased

				Controller.landPlots[col][row].setOwner(currentP);
				currentP.landOwned.add(newLand);

			} else {
				System.out.println("You do not have enough money to buy this land");
			}

			Land.landBuyEnable = false;//disable land buying for next turn
            if (selectPhase) {
                Timer.endTurn();
            }

		} else if (currentP.muleBuyEnable) {
			boolean muleBought = currentP.buyMule(new Mule(currentMuleType), newLand);//buy mule / return false if mule has been lost
			if (muleBought) {//if !muleLost
				Image mulePic =  new Image("gameConfig/UIFiles/Media/aMule.png");
				ImageView muleView = new ImageView();
				muleView.setImage(mulePic);
				muleView.setFitWidth(50);
				muleView.setPreserveRatio(true);
				grid.setConstraints(muleView, col, row, 1, 1);
				grid.getChildren().add(muleView);
			}
		}
	}

	public void muleBuyButtonClick(ActionEvent e) {
		String choice = muleChoice.getSelectionModel().getSelectedItem().toString();
		choice = choice.toUpperCase().substring(0, choice.indexOf(" "));
		currentMuleType = Mule.Type.valueOf(choice);
		Player currentP = Turns.getTurn();
		currentP.muleBuyEnable = true;
		Stage stage = (Stage) muleChoice.getScene().getWindow();
		stage.close();
	}
	//PUB
    public void pubButtonClicked(ActionEvent e) {
        newStage = new Stage();
        if (e.getSource() == pubButton) {
            newStage.setScene(Launcher.pubGambleScene);
            newStage.setTitle(Turns.getTurn().getName());
            newStage.show();
        }
        Stage stage = (Stage) pubButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void gambleButtonClicked(ActionEvent e) {
        newStage = new Stage();
        if (e.getSource() == gambleButton) {
            //System.out.println("BUTTON PRESSED");
            int timeLeft = Timer.getTimeLeft();
            Player p = Turns.getTurn();
            int moneyWon = p.gamble(timeLeft);
            Timer.endTurn();

            System.out.println("P's money = " + p.getMoney());
            //infoBarCreated = true;
            //infoBar();
            //infoBar.updateInfoBar();
            System.out.println("P's money = " + p.getMoney());

            newStage.setScene(Launcher.gambleConfirm);
            newStage.setTitle("Congratulations");
            newStage.show();
        }
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void gambleConfirm(ActionEvent e) {
        if (e.getSource() == gambleOkButton) {
        }
        Stage stage = (Stage) gambleOkButton.getScene().getWindow();
        stage.close();
    }

//STORE

    public void storeButtonClicked(ActionEvent e) {
        newStage = new Stage();
        if (e.getSource() == storeButton) {
            newStage.setScene(Launcher.storeScene);
            newStage.setTitle("Store");
            newStage.show();

            //foodCostLabel.setText("" + store.getFoodCost());
        }
        Stage stage = (Stage) storeButton.getScene().getWindow();
        stage.close();
    }

    //public void
}