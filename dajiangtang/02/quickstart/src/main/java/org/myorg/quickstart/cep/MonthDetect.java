package org.myorg.quickstart.cep;

import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.myorg.quickstart.cep.events.DSEvent;
import org.myorg.quickstart.util.WordCountData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MonthDetect {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        DataStream<String> text = env.fromElements(WordCountData.WORDS);

        DataStream<DSEvent> input = env.fromCollection(Arrays.asList(
                new DSEvent("月", 0.1, 1),
                new DSEvent("季", 0.1, 1),
                new DSEvent("年", 0.2, 1),
                new DSEvent("季", 0.1, 2),
                new DSEvent("月", 0.1, 2),
                new DSEvent("季", 0.1, 2),
                new DSEvent("年", 0.1, 2),
                new DSEvent("季", 0.1, 2),
                new DSEvent("月", 0.33, 3),
                new DSEvent("月", 0.33, 3),
                new DSEvent("月", 0.33, 3),
                new DSEvent("季", 0.33, 3),
                new DSEvent("年", 0.31, 3)
        ));
        Pattern<DSEvent, ?> mouthPattern = Pattern.<DSEvent>begin("start").where(
                new SimpleCondition<DSEvent>() {
                    @Override
                    public boolean filter(DSEvent dsEvent) throws Exception {
                        return dsEvent.getNsqx().equals("月");
                    }
                }
        ).next("middle").subtype(DSEvent.class).where(
                new SimpleCondition<DSEvent>() {
                    @Override
                    public boolean filter(DSEvent dsEvent) throws Exception {
                        return dsEvent.getNsqx().equals("季");
                    }
                }
        ).followedBy("end").where(
                new SimpleCondition<DSEvent>() {
                    @Override
                    public boolean filter(DSEvent dsEvent) throws Exception {
                        return dsEvent.getNsqx().equals("年");
                    }
                }
        );
        PatternStream<DSEvent> patternStream = CEP.pattern(input, mouthPattern);
        DataStream<String> sumWarning = patternStream.select(
                (Map<String, List<DSEvent>> pattern)->{
                    DSEvent first = (DSEvent) pattern.get("start").get(0);
                    DSEvent middle = (DSEvent) pattern.get("middle").get(0);
                    DSEvent end = (DSEvent) pattern.get("end").get(0);
                    if(end.getZsl() >= first.getZsl() && end.getZsl() >= middle.getZsl()){
                        return "年度>=月度 季度>=月度 成立";
                    }else{
                        return "不符合："+"年："+end.getZsl()+"季："+middle.getZsl()+"月："+first.getZsl();
                    }
            }
        );
        sumWarning.print();
        env.execute("Year,Season,Month");
    }
}
