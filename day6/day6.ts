/*
//Sampleinput
let time :number[] = [7, 15, 30]
let record :number[] = [9, 40, 200]
*/

/*
//input Part I
let time :number[] = [50, 74, 86, 85]
let record :number[] = [242, 1017, 1691, 1252]
*/

//Input Part II
let time :number[] = [50748685]
let record :number[] = [242101716911252]

let breakingRecord = 0;
let totBreakingRecord = 1;

for (let i = 0; i < time.length; i++) {
    let speed = 0;
    for (let j = 0; j <= time[i]; j++) {
        let distance :number = speed * (time[i]-j);
        if (distance > record[i]) {
            breakingRecord++;
        }
        speed++;
    }
    totBreakingRecord *= breakingRecord
    breakingRecord = 0;
}

console.log(time)
console.log(record)
console.log('Total gebrochene Rekorde: ' + totBreakingRecord)