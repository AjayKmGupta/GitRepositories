package ai.sahaj.snakeladder.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ai.sahaj.snakeladder.constants.Stats;
import ai.sahaj.snakeladder.dto.backend.StatsData;
import ai.sahaj.snakeladder.metric.prototype.MetricGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MetricGeneratorFactory {

	@Autowired
	@Qualifier("avgClimbAmt")
	private MetricGenerator<StatsData<Double>> avgAmtOfClimb;

	@Autowired
	@Qualifier("maxClimbAmt")
	private MetricGenerator<StatsData<Integer>> maxAmtOfClimb;

	@Autowired
	@Qualifier("minClimbAmt")
	private MetricGenerator<StatsData<Integer>> minAmtOfClimb;

	@Autowired
	@Qualifier("avgSlideAmt")
	private MetricGenerator<StatsData<Double>> avgAmtOfSlide;

	@Autowired
	@Qualifier("maxSlideAmt")
	private MetricGenerator<StatsData<Integer>> maxAmtOfSlide;

	@Autowired
	@Qualifier("minSlideAmt")
	private MetricGenerator<StatsData<Integer>> minAmtOfSlide;

	@Autowired
	@Qualifier("biggestClimb")
	private MetricGenerator<StatsData<Integer>> biggestClimb;

	@Autowired
	@Qualifier("biggestSlide")
	private MetricGenerator<StatsData<Integer>> biggestSlide;

	@Autowired
	@Qualifier("longestTurn")
	private MetricGenerator<StatsData<Integer>> longestTurn;

	@Autowired
	@Qualifier("avgRollsToWin")
	private MetricGenerator<StatsData<Double>> avgRollsToWin;

	@Autowired
	@Qualifier("maxRollsToWin")
	private MetricGenerator<StatsData<Integer>> maxRollsToWin;

	@Autowired
	@Qualifier("minRollsToWin")
	private MetricGenerator<StatsData<Integer>> minRollsToWin;

	@Autowired
	@Qualifier("avgLuckyRolls")
	private MetricGenerator<StatsData<Double>> avgLuckyRolls;

	@Autowired
	@Qualifier("maxLuckyRolls")
	private MetricGenerator<StatsData<Integer>> maxLuckyRolls;

	@Autowired
	@Qualifier("minLuckyRolls")
	private MetricGenerator<StatsData<Integer>> minLuckyRolls;

	@Autowired
	@Qualifier("avgUnluckyRolls")
	private MetricGenerator<StatsData<Double>> avgUnLuckyRolls;

	@Autowired
	@Qualifier("maxUnluckyRolls")
	private MetricGenerator<StatsData<Integer>> maxUnLuckyRolls;

	@Autowired
	@Qualifier("minUnluckyRolls")
	private MetricGenerator<StatsData<Integer>> minUnLuckyRolls;

	public MetricGenerator<? extends StatsData<? extends Number>> generateMetricGenerator(Stats stats) {
		MetricGenerator<? extends StatsData<? extends Number>> metricGenerator = null;
		switch (stats) {
		case AOCAVG:
			metricGenerator = avgAmtOfClimb;
			break;
		case AOCMAX:
			metricGenerator = maxAmtOfClimb;
			break;
		case AOCMIN:
			metricGenerator = minAmtOfClimb;
			break;
		case AOSAVG:
			metricGenerator = avgAmtOfSlide;
			break;
		case AOSMAX:
			metricGenerator = maxAmtOfSlide;
			break;
		case AOSMIN:
			metricGenerator = minAmtOfSlide;
			break;
		case BCINST:
			metricGenerator = biggestClimb;
			break;
		case BSINST:
			metricGenerator = biggestSlide;
			break;
		case LKRAVG:
			metricGenerator = avgLuckyRolls;
			break;
		case LKRMAX:
			metricGenerator = maxLuckyRolls;
			break;
		case LKRMIN:
			metricGenerator = minLuckyRolls;
			break;
		case LONGTN:
			metricGenerator = longestTurn;
			break;
		case RTWAVG:
			metricGenerator = avgRollsToWin;
			break;
		case RTWMAX:
			metricGenerator = maxRollsToWin;
			break;
		case RTWMIN:
			metricGenerator = minRollsToWin;
			break;
		case ULRAVG:
			metricGenerator = avgUnLuckyRolls;
			break;
		case ULRMAX:
			metricGenerator = maxUnLuckyRolls;
			break;
		case ULRMIN:
			metricGenerator = minUnLuckyRolls;
			break;
		default:
			log.error("Provided metric is not supported yet");
			break;
		}
		return metricGenerator;

	}
}
