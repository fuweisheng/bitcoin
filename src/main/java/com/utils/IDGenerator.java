package com.utils;

public class IDGenerator {
	/**
	 * 生成十六位十六进制钱包地址
	 * 
	 * UNIX_TIMESTAMP()转16进制（8位） + 4组两位十六进制随机数 共16位
	 * 
	 * @return
	 */
	public static String generatorIdHex() {
		long time = System.currentTimeMillis() / 1000;
		String strTime = Long.toHexString(time);
		int rdm[] = new int[4];
		rdm[0] = (int) (Math.random() * 239) + 16;
		String StrRdm0 = Long.toHexString(rdm[0]);
		rdm[1] = (int) (Math.random() * 239) + 16;
		String StrRdm1 = Long.toHexString(rdm[1]);
		rdm[2] = (int) (Math.random() * 239) + 16;
		String StrRdm2 = Long.toHexString(rdm[2]);
		rdm[3] = (int) (Math.random() * 239) + 16;
		String StrRdm3 = Long.toHexString(rdm[3]);

		return strTime + StrRdm0 + StrRdm1 + StrRdm2 + StrRdm3;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String id = generatorIdHex();
			System.out.println(id);
		}
	}
}
