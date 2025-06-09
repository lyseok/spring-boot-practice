/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	// alert(axios);

	const lprodGuSel = document.getElementById('lprodGu');
	const buyerIdSel = document.getElementById('buyerId');
	const lprodGuInitVal = lprodGuSel.dataset.initVal;
	const buyerIdInitVal = buyerIdSel.dataset.initVal;
	
	const getLprod = async () => {
		const lprodList = await axios.get(`/rest/lprod`)
			.then(res => res.data);
		if(lprodList){
			const data = lprodList.map(({lprodGu, lprodName}) => 
				`<option value='${lprodGu}'>${lprodName}</option>`).join('\n');
			lprodGuSel.innerHTML += data;
			lprodGuSel.value = lprodGuInitVal ?? ''; 			
		}
	}
	
	const getBuyer = async () => {
		const buyerList = await axios.get(`/rest/buyer`)
			.then(res => res.data);
		if(buyerList){
			const data = buyerList.map(({buyerId, buyerName, lprodGu}) => 
				`<option value='${buyerId}' class='${lprodGu}'>${buyerName}</option>`).join('\n');
			buyerIdSel.innerHTML += data;
			buyerIdSel.value = buyerIdInitVal ?? ''; 			
		}
	}
	
	lprodGuSel.addEventListener('change', (e) => {
		const selGu = e.target.value;
		buyerIdSel.querySelectorAll('option[class]').forEach(op => {
			if(op.classList.contains(selGu)) {
				op.style.display = "block";
			} else {
				op.style.display = "none";
			}
		})
		
	});
	
	getLprod();
	getBuyer();
});