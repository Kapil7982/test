SELECT sum(claimed_charge) as total_claimed_charge FROM document WHERE status ='EXPORTED';
SELECT insured_name, insured_address,claimed_charge FROM document inner JOIN batch ON document.batch_id = batch.id 
WHERE document.status ='TO_REPRICE' AND batch.customer_id IN (1,2); 