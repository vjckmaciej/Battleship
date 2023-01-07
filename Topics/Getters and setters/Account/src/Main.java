class Account {

    private long balance;
    private String ownerName;
    private boolean locked;

    public String getOwnerName() {
        return this.ownerName;
    }

    public long getBalance() {
        return this.balance;
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
