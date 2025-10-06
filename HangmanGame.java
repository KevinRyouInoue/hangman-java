import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import static java.lang.Math.sqrt;
public class HangmanGame {

    // ゲームで使用される単語のリスト
    private static final String[] WORDS = {"programming", "java", "computer", "algorithm", "developer", "keyboard", "internet"};
    // プレイヤーが間違えられる最大試行回数
    private static final int MAX_GUESSES = 7;

    public static void main(String[] args) {
        // ユーザーからの入力を受け取るためのScannerオブジェクト
        Scanner scanner = new Scanner(System.in);
        // ランダムな単語を選択するためのRandomオブジェクト
        Random random = new Random();

        // 秘密の単語を選択
        String secretWord = WORDS[random.nextInt(WORDS.length)];
        // プレイヤーが推測した文字を表示するための配列。最初はすべてアンダースコアで初期化
        char[] guessedWord = new char[secretWord.length()];
        Arrays.fill(guessedWord, '_');

        // プレイヤーが間違えた文字を格納するリスト
        List<Character> wrongGuesses = new ArrayList<>();
        // プレイヤーに残された試行回数
        int remainingGuesses = MAX_GUESSES;

        System.out.println("ハングマンゲームへようこそ！");
        System.out.println("単語を当ててください。");

        // ゲームのメインループ
        // 残り試行回数が0より大きく、かつまだ単語にアンダースコアが残っている間続ける
        while (remainingGuesses > 0 && String.valueOf(guessedWord).contains("_")) {
            System.out.println("\n---");
            // 現在の単語の状況を表示
            System.out.println("単語: " + String.valueOf(guessedWord));
            // 間違えた文字のリストを表示
            System.out.println("間違えた文字: " + wrongGuesses);
            // 残り試行回数を表示
            System.out.println("残り試行回数: " + remainingGuesses);
            System.out.print("文字を推測してください: ");

            // ユーザーからの入力を読み込み、小文字に変換
            String input = scanner.next().toLowerCase();

            // 入力バリデーション：1文字のアルファベットであるかを確認
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("無効な入力です。一文字のアルファベットを入力してください。");
                continue; // ループの最初に戻る
            }

            char guess = input.charAt(0);

            // 既に推測済みの文字かチェック（間違えた文字リストにあるか、またはすでに当たっているか）
            if (wrongGuesses.contains(guess) || String.valueOf(guessedWord).indexOf(guess) != -1) {
                System.out.println("その文字はすでに推測済みです。別の文字を選んでください。");
                continue; // ループの最初に戻る
            }

            boolean correctGuess = false;
            // 秘密の単語をループして、推測した文字が含まれているか確認
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedWord[i] = guess; // 正しければ、guessedWordを更新
                    correctGuess = true;
                }
            }

            // 推測結果に基づいてメッセージを表示し、残り試行回数を更新
            if (correctGuess) {
                System.out.println("正解です！");
            } else {
                System.out.println("不正解です。");
                wrongGuesses.add(guess); // 間違えた文字リストに追加
                remainingGuesses--; // 残り試行回数を減らす
            }
        }

        // ゲーム終了後の結果表示
        System.out.println("\n---");
        // 単語がすべて当てられたかどうかのチェック
        if (!String.valueOf(guessedWord).contains("_")) {
            System.out.println("おめでとうございます！単語を当てました: " + secretWord);
        } else {
            System.out.println("残念！ゲームオーバーです。");
            System.out.println("秘密の単語は: " + secretWord + " でした。");
        }

        scanner.close(); // Scannerをクローズ
    }
}
