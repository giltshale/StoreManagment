import java.util.Date;

public class Employee extends Customer {

    private int salary;
    private int workingHours;
    private int rank;


    public Employee(String userName, String password, String firstName, String lastName, int rank) {
        super(userName, password, firstName, lastName, false, 0, 0, null, new ShoppingCart());
        this.rank = rank;
    }

    public int getSalary() {
        return salary;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public int getRank() {
        return rank;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }


    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        String regular = "regular worker";
        String manager = "manager";
        String boardMember = "board member";
        String printedText = "";
        String fullName = this.firstName + " " + this.lastName;
        if (this.rank == KindOfWorker.REGULAR_WORKER.ordinal() + 1) {
            printedText = fullName + " (" + regular + ")" + "!\n";
        } else if (this.rank == KindOfWorker.MANAGER.ordinal() + 1) {
            printedText = fullName + " (" + manager + ")" + "!\n";
        } else if (this.rank == KindOfWorker.BOARD_MEMBER.ordinal() + 1) {
            printedText = fullName + " (" + boardMember + ")" + "!\n";
        }
        StringBuilder s = new StringBuilder(" ");
        s.append("number of buys: ")
                .append(super.getNumberOfBuys())
                .append("\n total sum of all buys: ")
                .append(super.getAllBuysSum())
                .append(" \n lats purchase made at: ");
        if (super.getLastVisit() == null) {
            s.append("didn't visit the store yet");
        } else {
            s.append(super.getLastVisit());
        }
        s.append("\n");

        printedText += s;
        return printedText;
    }
}
