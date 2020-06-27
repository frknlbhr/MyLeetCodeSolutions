/* Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
*/
public int[] CountBits(int num) {
	int[] res = new int[num + 1];
	res[0] = 0;
	
	for(int i=1 ; i<=num ; i++){
		int pow = 0;
		int temp = i;
		
		while(temp > 1){
			temp /= 2;
			pow++;
		}
		
		res[i] = res[i - (int)Math.Pow(2,pow)] + 1;
	}
	
	return res;
}