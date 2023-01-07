class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        // implement me
        if (this.hours == 12 && this.minutes == 59){
            this.hours = 1;
            this.minutes = 0;
        }
        else if (this.minutes == 59) {
            this.minutes = 0;
            this.hours += 1;
        }
        else {
            this.minutes += 1;
        }
    }
}