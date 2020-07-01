package machine;
import java.util.Scanner;
public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyCoffeMachine myCoffeMachine = new MyCoffeMachine(400, 540, 120, 9, 550);
        myCoffeMachine.start();

        while (myCoffeMachine.getState() != State.EXIT) {
            myCoffeMachine.optionInput(scanner.next());
        }
    }
}

enum State {
    READY, EXIT, COFFEE_CHOICE, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_POSIBLES;
}

class MyCoffeMachine {
    private int water;
    private int milk;
    private int beans;
    private int posibles;
    private int money;
    private String input;
    // Aca va una variable con el estado bebe;
    private State state = State.READY;

    public MyCoffeMachine(int water, int milk, int beans, int posibles, int money){
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.posibles = posibles;
        this.money = money;
    }

    public void start() {
        this.state = State.READY;
        System.out.println();
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
    }

    public State getState() {
        return this.state;
    }

    public void optionInput(String input) {
        this.input = input;

        switch (this.state) {
            case READY:
                selectFirstOptions();
                break;
            case COFFEE_CHOICE:
                buy();
                break;
            case FILL_WATER:
            case FILL_MILK:
            case FILL_BEANS:
            case FILL_POSIBLES:
                fill();
                break;
            default:
                start();
                break;
        }
    }
    private void selectFirstOptions() {
        System.out.println();
        switch (this.input) {
            case "buy":
                buy();
                break;
            case "fill":
                fill();
                break;
            case "take":
                take();
                break;
            case "remaining":
                remaining();
                break;
            case "exit":
                exit();
                break;
            default:
                break;
        }
    }

    private void buy() {
        switch (this.state) {
            case READY:
                System.out.println();
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                this.state = State.COFFEE_CHOICE;
                break;
            case COFFEE_CHOICE:
                switch (this.input) {
                    case "1":
                        if ( this.water - 250 >= 0 ) {
                            this.water = this.water - 250;
                            if ( this.beans - 16 >= 0 ) {
                                this.beans = this.beans - 16;
                                this.posibles = this.posibles - 1;
                                this.money = money + 4;
                            } else {
                                System.out.println("Sorry, not enough beans!");
                                break;
                            }
                        } else {
                            System.out.println("Sorry, not enough water!");
                            break;
                        }
                        System.out.println("I have enough resources, making you a coffee!");
                        break;
                    case "2":
                        if ( this.water - 350 >= 0 ) {
                            this.water = this.water - 350;

                            if ( this.milk - 75 >= 0 ) {
                                this.milk = this.milk - 75;

                                if ( this.beans - 20 >= 0 ) {
                                    this.beans = this.beans - 20;
                                    this.posibles = this.posibles - 1;
                                    this.money = this.money + 7;
                                } else {
                                    System.out.println("Sorry, not enough beans!");
                                    break;
                                }

                            } else {
                                System.out.println("Sorry, not enough milk!");
                                break;
                            }
                        } else {
                            System.out.println("Sorry, not enough water!");
                            break;
                        }
                        System.out.println("I have enough resources, making you a coffee!");
                        break;
                    case "3":
                        if ( this.water - 200 >= 0 ) {
                            this.water = this.water - 200;

                            if ( this.milk - 100 >= 0 ) {
                                this.milk = this.milk - 100;

                                if ( this.beans - 12 >= 0 ) {
                                    this.beans = this.beans - 12;
                                    this.posibles = this.posibles - 1;
                                    this.money = this.money + 6;
                                } else {
                                    System.out.println("Sorry, not enough beans!");
                                    break;
                                }
                            } else {
                                System.out.println("Sorry, not enough milk!");
                                break;
                            }
                        } else {
                            System.out.println("Sorry, not enough water!");
                            break;
                        }
                        System.out.println("I have enough resources, making you a coffee!");
                        break;
                    case "back":
                        break;
                    default:
                        break;
                }
                start();
                break;
            default:
                start();
                break;
        }

    }

    private void fill() {
        switch (this.state) {
            case READY:
                System.out.println("Write how many ml of water do you want to add:");
                this.state = State.FILL_WATER;
                break;
            case FILL_WATER:
                this.water = this.water + Integer.parseInt(this.input);
                System.out.println("Write how many ml of milk do you want to add:");
                this.state = State.FILL_MILK;
                break;
            case FILL_MILK:
                this.milk = this.milk + Integer.parseInt(this.input);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                this.state = State.FILL_BEANS;
                break;
            case FILL_BEANS:
                this.beans = this.beans + Integer.parseInt(this.input);
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                this.state = State.FILL_POSIBLES;
            case FILL_POSIBLES:
                this.posibles = this.posibles + Integer.parseInt(this.input);
                start();
                break;
            default:
                start();
        }
    }

    private void take() {
        System.out.println("I gave you " + money);
        money = 0;
        start();
    }

    private void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.beans + " of coffee beans");
        System.out.println(this.posibles + " of disposable cups");
        System.out.println(this.money + " of money");
        System.out.println();
        start();
    }

    private void exit() {
        this.state = State.EXIT;
    }
}
