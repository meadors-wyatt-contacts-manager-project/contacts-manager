# Where to go from here

The framework is designed for you to have fun while continuing to learn Java and gain experience programming. Here are a few suggestions for adding to this little tutorial game.

- Add some more rooms to the maze. It is a maze after all, and should probably be a more complicated.
- Add a healing fountain that will restore the player's health when visited one time only.
- Add some gold to the maze that the player can pick up. This will also require that the `Player` has a gold counter and some accessors.
- Add a merchant who sells a sword to give the player an "edge" (har har) when fighting the goblin.
- Make the goblin tougher to fight, which might require you to also implement some of the above suggestions.

### Add some pizazz to your screens

While console games cannot compete visually with modern video game graphics, a little color and sound go a long way. The `ConsoleColors` helper class has some ANSI codes that will add color to the text on your screen. And adding sound to Java programs these days is easy. 

Replace the code in your `WelcomeScreen` with the below code. Note that several of these methods have more than 20 lines of code each and are in need of some serious refactoring/cleaning. 

`WelcomeScreen.java`

```java
package docrob.cag.mygame.screens;

import docrob.cag.framework.screens.Screen;
import docrob.cag.framework.screens.ScreenManager;
import docrob.cag.framework.state.Game;
import docrob.cag.framework.utils.ConsoleColors;

import javax.sound.midi.*;

public class WelcomeScreen extends Screen {

    @Override
    public void show() {
        System.out.println(ConsoleColors.ANSI_CLEAR);

        System.out.printf("%s%sDocRob Presents...%s\n", ConsoleColors.ANSI_BG_BLACK, ConsoleColors.ANSI_FG_GREEN, ConsoleColors.ANSI_RESET);

        playMusicPart1();

        // from https://www.asciiart.eu/art-and-design/patterns
        System.out.printf("%s%s_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|%s                   %s|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_BG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|__%s%s    The  MAZE      %s%s__|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_BG_BLACK, ConsoleColors.ANSI_FG_WHITE, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|%s%s   of  DOOM!!1!    %s%s|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_BG_BLACK, ConsoleColors.ANSI_FG_WHITE, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|__%s                   %s__|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_BG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|__%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);
        System.out.printf("%s%s_|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|___|%s\n", ConsoleColors.ANSI_FG_BLACK, ConsoleColors.ANSI_BG_RED, ConsoleColors.ANSI_RESET);

        playMusicPart2();
    }

    @Override
    protected void handleInput() {
        Game.getInstance().getInput().getString(ConsoleColors.ANSI_FG_RED + "\nPress Enter to continue." +  ConsoleColors.ANSI_RESET);

        ScreenManager.addScreen(new MainScreen());
    }

    /*
    from
    https://stackoverflow.com/questions/16462854/midi-beginner-need-to-play-one-note
    and
    https://virtualpiano.net/
     */
    private void playMusicPart1() {
        try{
            /* Create a new Sythesizer and open it. Most of
             * the methods you will want to use to expand on this
             * example can be found in the Java documentation here:
             * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
             */
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument

            mChannels[0].noteOn(60, 100);//On channel 0, play note number 60 with velocity 100
            try { Thread.sleep(1500); // wait time in milliseconds to control duration
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }

            mChannels[0].noteOn(59, 100);//On channel 0, play note number 60 with velocity 100
            mChannels[0].noteOn(55, 100);//On channel 0, play note number 60 with velocity 100
            try { Thread.sleep(1500); // wait time in milliseconds to control duration
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
            mChannels[0].noteOff(60);//turn off the note
            mChannels[0].noteOff(59);//turn off the note
            mChannels[0].noteOff(59);//turn off the note


        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playMusicPart2() {
        try{
            /* Create a new Sythesizer and open it. Most of
             * the methods you will want to use to expand on this
             * example can be found in the Java documentation here:
             * https://docs.oracle.com/javase/7/docs/api/javax/sound/midi/Synthesizer.html
             */
            Synthesizer midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();

            //get and load default instrument and channel lists
            Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = midiSynth.getChannels();

            midiSynth.loadInstrument(instr[0]);//load an instrument

            mChannels[0].noteOn(40, 100);//On channel 0, play note number 60 with velocity 100
            mChannels[0].noteOn(36, 100);//On channel 0, play note number 60 with velocity 100
            mChannels[0].noteOn(28, 100);//On channel 0, play note number 60 with velocity 100
            try { Thread.sleep(3000); // wait time in milliseconds to control duration
            } catch( InterruptedException e ) {
                e.printStackTrace();
            }
            mChannels[0].noteOff(40);//turn off the note
            mChannels[0].noteOff(36);//turn off the note
            mChannels[0].noteOff(28);//turn off the note


        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }
}
```