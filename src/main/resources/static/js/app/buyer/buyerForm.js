/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
	// alert(axios);
	
	const CPATH = document.body.dataset.contextPath;
	const lprodGuSel = document.getElementById('lprodGu');
	const initVal = lprodGuSel.dataset.initVal;
	const getLprod = async () => {
		const lprodList = await axios.get(`/rest/lprod`)
			.then(res => res.data);
			console.log("lprodList",lprodList);
		if(lprodList){
			
			const data = lprodList.map(({lprodGu, lprodName}) => 
				`<option value='${lprodGu}'>${lprodName}</option>`).join('\n');
			lprodGuSel.innerHTML += data;
			lprodGuSel.value = initVal ?? ''; 			
		}

	}
	
	getLprod();

});