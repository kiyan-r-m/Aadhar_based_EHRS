/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIs;

import Beans.AdminBeanLocal;
import ReportModels.BloodGroupFrequency;
import ReportModels.DateWiseCaseFrequency;
import ReportModels.DiseaseToFrequency;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author krdmo
 */
@Named(value = "adminReportsManagedBean")
@SessionScoped
public class AdminReportsManagedBean implements Serializable {

    @EJB
    AdminBeanLocal abl;

    private BarChartModel barModel, mixedModel, acBarModel;

    private long doctorCounter, accessCounter, doctorDensity;
    private String disease, state;
    List<DiseaseToFrequency> topDiseases;
    LocalDate startDate;

    public AdminReportsManagedBean() {
        disease = state = null;
        startDate = LocalDate.now();
        startDate = startDate.minusMonths(2);

    }

    @PostConstruct
    public void init() {
        createBgBarModel();
        createCasesMixedModel();
        createACBarModel();
    }

    public BarChartModel getMixedModel() {
        return mixedModel;
    }

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public void createCasesMixedModel() {
        try {

            mixedModel = new BarChartModel();
            ChartData data = new ChartData();

            BarChartDataSet dataSet = new BarChartDataSet();
            List<Number> values = new ArrayList<>();
            List<Object> values2 = new ArrayList<>();
            List<String> labels = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");

            LocalDate ld = LocalDate.of(2023, Month.APRIL, 1);

            ArrayList<DateWiseCaseFrequency> table = returnCasesDataset();

            for (DateWiseCaseFrequency xdata : table) {
                values.add(xdata.getFrequency());
                values2.add(xdata.getFrequency());
                labels.add(xdata.getFrequencyDate().toString());
            }
            dataSet.setData(values);
            //dataSet.setLabel("Bar Dataset");
            dataSet.setBorderColor("rgba(233, 86, 112, 1)");
            dataSet.setBackgroundColor("rgba(233, 86, 112, 0.8)");

            LineChartDataSet dataSet2 = new LineChartDataSet();

            dataSet2.setData(values2);
            //dataSet2.setLabel("Line Dataset");
            dataSet2.setFill(false);
            dataSet2.setBorderColor("rgb(67, 47, 112)");

            data.addChartDataSet(dataSet);
            data.addChartDataSet(dataSet2);

            data.setLabels(labels);

            

            mixedModel.setData(data);

            //Options
            BarChartOptions options = new BarChartOptions();
            CartesianScales cScales = new CartesianScales();
            CartesianLinearAxes linearAxes = new CartesianLinearAxes();
            linearAxes.setOffset(true);
            CartesianLinearTicks ticks = new CartesianLinearTicks();
            ticks.setBeginAtZero(true);
            linearAxes.setTicks(ticks);
            
            Title title = new Title();
            title.setDisplay(true);
            title.setText("Country-Wide Cases");
            options.setTitle(title);

            cScales.addYAxesData(linearAxes);
            options.setScales(cScales);
            mixedModel.setOptions(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createBgBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();

        List<String> labels = new ArrayList<>();
        List<Number> values = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        ArrayList<BloodGroupFrequency> Bardata = (ArrayList) abl.getBloodGroupFrequency();
        for (BloodGroupFrequency valueset : Bardata) {
            values.add(valueset.getFrequency());
            labels.add(valueset.getBlood_group_name());
            bgColor.add("rgba(255, 99, 132, 0.2)");
            borderColor.add("rgb(255, 99, 132)");
        }
        barDataSet.setData(values);

        barDataSet.setBackgroundColor(bgColor);

        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        //ticks.setStepSize(10);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Blood Group Distribution");
        options.setTitle(title);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(700);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }
    
    public void createACBarModel() {
        acBarModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();

        List<String> labels = new ArrayList<>();
        List<Number> values = new ArrayList<>();
        List<String> bgColor = new ArrayList<>();
        List<String> borderColor = new ArrayList<>();
        values.add(abl.getTotalChronicCases());
        values.add(abl.getTotalAcuteCases());
        for (int i = 1; i<2; i++) {
            bgColor.add("rgba(233, 86, 112, 0.8)");
            borderColor.add("rgb(67, 47, 112)");
        }
        labels.add("Chronic");
        labels.add("Acute");
        barDataSet.setData(values);

        barDataSet.setBackgroundColor(bgColor);

        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        data.setLabels(labels);
        acBarModel.setData(data);
        

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        //ticks.setStepSize(10);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Currently active Acute vs. Chronic cases");
        options.setTitle(title);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(700);
        options.setAnimation(animation);
        options.setBarThickness(35);

        acBarModel.setOptions(options);
    }

    ArrayList<DateWiseCaseFrequency> returnCasesDataset() {
        ArrayList<DateWiseCaseFrequency> spData = (ArrayList) abl.getCasesFrequency(disease, startDate, state);
        ArrayList<DateWiseCaseFrequency> returnData = new ArrayList<>();
        LocalDate endDate = LocalDate.now();
        for (int j = 0; startDate.compareTo(endDate) <= 0; startDate = startDate.plusDays(1L)) {
            DateWiseCaseFrequency cf = new DateWiseCaseFrequency();
            cf.setFrequencyDate(startDate);
            if (startDate.compareTo(spData.get(j).getFrequencyDate()) == 0) {
                cf.setFrequency(spData.get(j).getFrequency());
                if (j < spData.size() - 1) {
                    j++;
                }
            } else {
                cf.setFrequency(0L);
            }
            returnData.add(cf);
        }

        return returnData;
    }

    public BarChartModel getAcBarModel() {
        return acBarModel;
    }

    public void setAcBarModel(BarChartModel acBarModel) {
        this.acBarModel = acBarModel;
    }
    
    

    public long getDoctorCounter() {
        this.doctorCounter = abl.getAllUsersFrequency(3);
        return this.doctorCounter;
    }

    public void setDoctorCounter(long doctorCounter) {
        this.doctorCounter = doctorCounter;
    }

    public long getAccessCounter() {
        accessCounter = abl.getTotalAccess();
        return accessCounter;
    }

    public void setAccessCounter(long accessCounter) {
        this.accessCounter = accessCounter;
    }

    public long getDoctorDensity() {
        doctorDensity = (abl.getAllUsersFrequency(3)*1000)/(abl.getAllUsersFrequency(1)+abl.getAllUsersFrequency(2)+abl.getAllUsersFrequency(3));
        return doctorDensity;
    }

    public void setDoctorDensity(long doctorDensity) {
        this.doctorDensity = doctorDensity;
    }

    public List<DiseaseToFrequency> getTopDiseases() {
        topDiseases = (List)abl.getTopCases();
        return topDiseases;
    }

    public void setTopDiseases(List<DiseaseToFrequency> topDiseases) {
        this.topDiseases = topDiseases;
    }
    
    
    
    

}
