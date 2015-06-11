import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

import org.junit.Test;

import xx.xxclass.XXClassManager;
import xx.xxclass.XXInstance;

public class TestHighLow {

  @Test
  public void testHiLo() {

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    XXClassManager cm = new XXClassManager();

    cm.newClassDef("Utils")

    .defFn("println", (mgr, self, pars) -> {
      System.out.println(pars[0].asJavaObject(Object.class));
      return self;
    })

    .defFn("print", (mgr, self, pars) -> {
      System.out.print(pars[0].asJavaObject(Object.class));
      return self;
    });

    cm.newClassDef("Game", "Utils")

    .redefFn("__init__", old -> (mgr, self, pars) -> {
      self.put("lower", pars[0]);
      self.put("upper", pars[1]);
      self.put("rng", mgr.$$(new Random()));
      return self;
    })

    .defFn("play", (mgr, self, pars) -> {
      XXInstance number = self.$("newNumber");
      self.put("guessCounter", mgr.$$(0));

      XXInstance.WHILE(self.get("playing"), () -> {

        self.put("guessCounter", self.get("guessCounter").$("+", mgr.$$(1)));

        XXInstance guess = self.$("getGuess");

        XXInstance.IF(

        guess.$("<", number),

        () -> self.$("println", mgr.$$("Too low!")),

        guess.$(">", number),

        () -> self.$("println", mgr.$$("Too high!")),

        () -> self.$("win")

        );

      });

      return self.get("guessCounter");
    })

    .defFn("newNumber", (mgr, self, pars) -> {
      self.put("playing", mgr.$$(true));
      XXInstance lower = self.get("lower");
      XXInstance upper = self.get("upper");

      return mgr.$$(self.get("rng").asJavaObject(Random.class).nextInt(upper.asJavaObject(Integer.class))).$("+", lower);
    })

    .defFn("getGuess", (mgr, self, pars) -> {
      try {
        self.$("print", mgr.$$("> "));
        return mgr.$$(Integer.valueOf(in.readLine()));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    })

    .defFn("win", (mgr, self, pars) -> {
      self.$("println", mgr.$$("A winner is you!"));
      self.$("println", self.get("guessCounter").$("..", mgr.$$(" guesses!")));
      self.put("playing", mgr.$$(false));
      return self;
    });

    XXInstance gameInstance = cm.newInstance("Game", cm.$$(0), cm.$$(100));

    XXInstance gc = gameInstance.$("play");

    System.out.println(gc.asJavaObject(Number.class).intValue());

  }
}
