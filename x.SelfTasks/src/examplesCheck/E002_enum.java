package examplesCheck;


public class E002_enum {
    public static void main(String args[]) {
        User user = new User();
        user.setCounter(TestEnum.ONE);
        System.out.println(user.getCounter());
        for (TestEnum te : TestEnum.values()) {
            System.out.println(te.value);
        }
    }

    public static enum TestEnum {
        ONE("1"),
        TWO("2"),
        THREEE("3");

        private String value;

        private TestEnum(String value) {
            this.value = value;
        }

        public String getDisplayedName() {
            return this.value;
        }

    }

    public static class User {
        private TestEnum counter;

        public TestEnum getCounter() {
            return counter;
        }

        public void setCounter(TestEnum counter) {
            this.counter = counter;
        }
    }
}
