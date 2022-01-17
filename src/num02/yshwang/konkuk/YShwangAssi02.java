package num02.yshwang.konkuk;

import java.io.IOException;
import java.util.Scanner;

public class YShwangAssi02 {

	public static Scanner scanner = new Scanner(System.in);

	static final int ROW = 4; // 상수(final)->주차공간 행 : 4
	static final int COL = 3; // 상수(final)->주차공간 열 : 3

	public static void main(String[] args) {

		String[][] parkingSpace = new String[ROW][COL];

		int menu = 0;
		do {
			showMap(parkingSpace);
			System.out.println();
			System.out.println("1) 주차하기 2) 출차하기 3) 종료");
			System.out.print("메뉴를 선택하세요 : ");
			menu = scanner.nextInt();

			if (menu < 1 || menu > 3) {
				System.out.println("메뉴 번호를 확인 후 다시 입력해 주세요.");
				System.out.println();
				continue;
			}
			switch (menu) {
			case 1:
				Parking(parkingSpace);
				break;
			case 2:
				PullOut(parkingSpace);
				break;

			case 3:
				System.out.println("시스템을 종료합니다.");
				break;
			}
		} while (menu != 3);
	}

	public static void showMap(String[][] parkingSpace) {
		System.out.println("주차관리 (202110547 황윤선)\n");
		System.out.print("  ");
		for (int i = 0; i < parkingSpace[0].length; i++) {
			System.out.print((i + 1) + " ");
		}
		System.out.println();

		int line = 1;
		for (String[] row : parkingSpace) {
			System.out.print(line + " ");
			for (String col : row) {
				if (col == null) // 차량정보가 없다(주차되어있지 않다)
					System.out.print("♡ ");
				else // 주차됨
					System.out.print("♥ ");

			}
			line++;
			System.out.println();
		}
	}

	public static void Parking(String[][] parkingSpace) {
		System.out.println();
		System.out.print("주차할 위치를 선택해 주세요 (입력예 : 2 1) :");
		int row = scanner.nextInt();
		int col = scanner.nextInt();

		if ((row < 1 || row > ROW) || (col < 1 || col > COL)) {
			System.out.println("위치 번호를 확인해 주세요. 처음부터 다시 진행해 주세요.");
			System.out.println();
			return;
		}

		if (parkingSpace[row - 1][col - 1] == null) {
			System.out.print("차량 번호를 입력해 주세요 (입력예 : 20가1234) : ");
			String carNumber = scanner.next();
			System.out.print("차량 번호 " + carNumber + " 맞습니까?(y/n)");
			char check;
			try {
				check = (char) System.in.read(); // 그냥 scanner.next()사용해도됨
				if (check == 'y') {
					parkingSpace[row - 1][col - 1] = carNumber;
					System.out.println(carNumber + "차량의 주차를 완료하였습니다.");
				} else {
					System.out.println("처음부터 다시 진행해 주세요.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void PullOut(String[][] parkingSpace) {
		System.out.println();
		System.out.println("차량 번호를 입력해 주세요 : ");
		String carNumber = scanner.next();

		for (String[] row : parkingSpace) {
			for (int i = 0; i < row.length; i++) {
				if (row[i] != null && row[i].equals(carNumber)) {
					System.out.println(row[i] + " 차량이 출차되었습니다. 안녕히 가세요.");
					System.out.println();
					row[i] = null;
					return;
				}
			}
		}
		System.out.println("차량이 존재하지 않습니다. 차량번호 확인후 처음부터 다시 진행해 주세요.");
		System.out.println();
	}
}
