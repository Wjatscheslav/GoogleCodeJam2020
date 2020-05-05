package qualification.ppp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution
{
  public static void main(String[] args)
  {
    List<String> output = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    int numberOfTestCases = Integer.valueOf(sc.nextLine());
    for (int i = 0; i < numberOfTestCases; i++)
    {
      int activitiesCount = Integer.valueOf(sc.nextLine());
      List<Activity> activities = new ArrayList<>(activitiesCount);
      for (int j = 0; j < activitiesCount; j++)
      {
        activities.add(new Activity(j, sc.nextLine().split(" ")));
        activities.sort(Comparator.comparing(activity -> activity.start));
      }
      output.add(splitActivities(activities, i + 1));
    }

    for (String s : output)
    {
      System.out.println(s);
    }
  }

  private static String splitActivities(List<Activity> activities, int testCaseNumber)
  {
    StringBuilder sb = new StringBuilder();

    Activity cameron = null;
    Activity jamie = null;
    for (Activity activity : activities)
    {
      if (cameron == null || cameron.end <= activity.start)
      {
        cameron = activity;
        activity.assignee = "C";
        continue;
      }
      else if (jamie == null || jamie.end <= activity.start)
      {
        jamie = activity;
        activity.assignee = "J";
        continue;
      }
      return String.format("Case #%d: IMPOSSIBLE", testCaseNumber);
    }
    activities.sort(Comparator.comparing(activity -> activity.number));
    for (Activity a : activities)
    {
      sb.append(a.assignee);
    }

    return String.format("Case #%d: %s", testCaseNumber, sb.toString());
  }

  static class Activity
  {
    Activity(int number, String[] startAndEnd)
    {
      this.number = number;
      this.start = Integer.valueOf(startAndEnd[0]);
      this.end = Integer.valueOf(startAndEnd[1]);
    }

    int number;
    int start;
    int end;
    String assignee;
  }
}
