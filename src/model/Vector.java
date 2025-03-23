package model;

public class Vector {
    private String polarDirection;

    //Vecteur de deplacement en verticale a l'init
    private int xDirection=0;
    private int yDirection=1;
    private int id;

    public Vector(){
        this.polarDirection = "SOUTH";
        this.id = 0;
    }
    
    public Vector(Coord c1, Coord c2, int id){
        this.xDirection = c2.getX() - c1.getX();
        this.yDirection = c2.getY() - c1.getY();
        if(this.xDirection == 1 && this.yDirection == 0){
            this.polarDirection = "EST";
        }
        else if(this.xDirection == -1 && this.yDirection == 0){
            this.polarDirection = "WEST";
        }
        else if(this.yDirection == -1 && this.xDirection == 0){
            this.polarDirection = "NORTH";
        }
        else if(this.yDirection == 1 && this.xDirection == 0){
            this.polarDirection = "SOUTH";
        }
        else{
            //this.xDirection = 0;
            //this.yDirection = 0;
            this.polarDirection = "NULL";
        }
        this.id = id;
    }

    public void turnVector(String turn){
        System.out.println("player " + this.id + " is turned to "+ this.polarDirection + "...\n");
        if(turn=="STOP"){
            this.xDirection=0;
            this.yDirection=0;
            this.polarDirection="NULL";
        }
        if(this.polarDirection=="NORTH"){
                if(turn == "LEFT"){

                    System.out.println("...and go west\n");
                    this.xDirection=-1;
                    this.yDirection=0;
                    this.polarDirection="WEST";

                }

                if(turn == "RIGHT"){
                    System.out.println("...and go est\n");
                    this.xDirection=1;
                    this.yDirection=0;
                    this.polarDirection="EST";
                    
                }
                
            } 
        else if(this.polarDirection=="SOUTH"){
            if(turn == "LEFT"){
                System.out.println("...and go est\n");
                this.xDirection=1;
                this.yDirection=0;
                this.polarDirection="EST";
            }
            if(turn == "RIGHT"){
                System.out.println("...and go west\n");
                this.xDirection=-1;
                this.yDirection=0;
                this.polarDirection="WEST";
                
            }
            
        }
        else if( this.polarDirection=="EST"){
            if(turn == "LEFT"){
                this.xDirection=0;
                this.yDirection=-1;
                System.out.println("...and go north\n");
                this.polarDirection="NORTH";
            }
            if(turn == "RIGHT"){
                this.xDirection=0;
                this.yDirection=1;
                System.out.println("...and go south\n");
                this.polarDirection="SOUTH";
                
            }
            
        }
        else if(this.polarDirection=="WEST"){
            if(turn == "LEFT"){
                this.xDirection=0;
                this.yDirection=1;
                System.out.println("...and go south\n");
                this.polarDirection="SOUTH";
            }
            if(turn == "RIGHT"){
                this.xDirection=0;
                this.yDirection=-1;
                System.out.println("...and go north\n");
                this.polarDirection="NORTH";
            }
        }
        else{
            System.out.println("...keep direction\n");
            this.polarDirection="NULL";

        }
    }

    public int getXDirection() {
        return this.xDirection;
    }

    public int getYDirection() {
        return this.yDirection;
    }

    public String getPolaDirection(){
        return this.polarDirection;
    }

    public void setID(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return ("V = (" + xDirection + ", " + yDirection + ") " + this.polarDirection);
    }

}
