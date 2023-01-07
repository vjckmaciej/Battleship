class Util {
    // correct this method to avoid NPE
    public static void printLength(String name) {
        String pusty = null;
        try {
            System.out.println(name.length());
        }
        catch (Exception e){
            System.out.println("null");
        }
            
        
    }
}
